package com.example.langt.viewmodel;

import com.deepl.api.Language;
import com.example.langt.model.Sentence;
import com.example.langt.sqlite.SQLiteJDBC;
import com.example.langt.viewmodel.Commands.*;
import com.example.langt.viewmodel.ShowSnackbar.Snackbar;
import com.example.langt.viewmodel.Translator.LanguageTranslator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class TranslationViewController implements Initializable {

    @FXML
    private ComboBox<String> sourceLanguageComboBox;

    @FXML
    private ComboBox<String> targetLanguageComboBox;

    @FXML
    private Button languageSwapButton;

    @FXML
    private TextArea sourceTextArea;

    @FXML
    private TextArea targetTextArea;

    @FXML
    private Button deleteTextButton;

    @FXML
    private Button copyTextButton;


    @FXML
    private VBox rootVBox;

    private Snackbar snackbar;
    private LanguageTranslator translator;

    // commands
    private ButtonClickedReceiver buttonClickedReceiver;
    private ICommand copyTextCommand;
    private ICommand deleteCommand;
    private ICommand swapLanguagesCommand;
    private static final int MAX_CHAR_LIMIT = 500;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translator = LanguageTranslator.getInstance();
        snackbar = Snackbar.getInstance(this.rootVBox);
        snackbar.updatePane(this.rootVBox);

        // initialize commands
        buttonClickedReceiver = new ButtonClickedReceiver();
        copyTextCommand = new CopyTextCommand(buttonClickedReceiver, targetTextArea, rootVBox);
        deleteCommand = new DeleteTextAreaCommand(buttonClickedReceiver, sourceTextArea, targetTextArea);
        swapLanguagesCommand = new SwapLanguagesCommand(buttonClickedReceiver, sourceTextArea, targetTextArea, sourceLanguageComboBox, targetLanguageComboBox, rootVBox);


        // set commands
        copyTextButton.setOnAction(e -> copyTextCommand.execute());
        deleteTextButton.setOnAction(e -> deleteCommand.execute());
        languageSwapButton.setOnAction(e -> swapLanguagesCommand.execute());



        sourceLanguageComboBox.getItems().add("Detect Language");
        sourceLanguageComboBox.getItems().addAll(translator.getSourceLanguages().stream().map(Language::getName).toList());
        targetLanguageComboBox.getItems().addAll(translator.getTargetLanguages().stream().map(Language::getName).toList());


        sourceLanguageComboBox.getSelectionModel().selectFirst();
        targetLanguageComboBox.getSelectionModel().select("English (American)");

        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        String[] lastClipboardContent = {null};
        boolean[] isFirstTime = {true};

        Timeline clipboardCheck = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (systemClipboard.hasString()) {
                String clipboardText = systemClipboard.getString();
                if (!clipboardText.equals(lastClipboardContent[0])) {
                    if (!isFirstTime[0]) {
                        sourceTextArea.setText(clipboardText);
                        sourceLanguageComboBox.getSelectionModel().selectFirst();
                        if (clipboardText.length() <= MAX_CHAR_LIMIT) {
                            this.translateText();

                            /*if (targetTextArea.getText().length() <= 100) {
                                Notifications.create()
                                        .title(sourceLanguageComboBox.getValue() + " -> " + targetLanguageComboBox.getValue())
                                        .text(targetTextArea.getText())
                                        .darkStyle()
                                        .show();
                            }*/


                        }


                    }
                    lastClipboardContent[0] = clipboardText;
                    isFirstTime[0] = false;
                }
            }
        }));

        clipboardCheck.setCycleCount(Timeline.INDEFINITE);
        clipboardCheck.play();


        // TextFormatter ile karakter sınırı ekleme
        sourceTextArea.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= MAX_CHAR_LIMIT ? change : null));
    }




    @FXML
    public void translateText() {

        try {
            String source_text = sourceTextArea.getText();
            String sourceLanguage = sourceLanguageComboBox.getValue();
            String targetLanguage = targetLanguageComboBox.getValue();

            if (source_text.isEmpty()) {
                snackbar.show("Please enter a text to translate");
                return;
            }

            Sentence sentence = translator.translateText(source_text, sourceLanguage, targetLanguage);
            SQLiteJDBC.getInstance().insert(sentence);

            // çeviriyi göster
            targetTextArea.setText(sentence.getTarget_text());

            // kaynak dil otomatik algılamada ise kaynak dili değerini değiştir
            if (sourceLanguage.equals("Detect Language")) {
                sourceLanguageComboBox.getSelectionModel().select(sentence.getSource_lang());
            }

        } catch (Exception e) {
            targetTextArea.setText("Translation Error");
            snackbar.show("You probably don't have an internet connection. Please check your connection and try again.");
        }
    }

    @FXML
    public void edit(Sentence sentence){

        sourceTextArea.setText(sentence.getSource_text());
        targetTextArea.setText(sentence.getTarget_text());

        sourceLanguageComboBox.getSelectionModel().select(sentence.getSource_lang());
        targetLanguageComboBox.getSelectionModel().select(sentence.getTarget_lang());

    }

    @FXML
    public void switchToHistoryScreen(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/langt/HistoryTranslationsScreen.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}
