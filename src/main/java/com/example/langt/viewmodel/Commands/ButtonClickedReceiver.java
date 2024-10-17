package com.example.langt.viewmodel.Commands;


import com.example.langt.viewmodel.ShowSnackbar.Snackbar;
import com.example.langt.viewmodel.Translator.LanguageTranslator;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;

public class ButtonClickedReceiver {


    public void deleteTextArea(TextArea sourceTextArea, TextArea targetTextArea) {
        sourceTextArea.clear();
        targetTextArea.clear();
    }

    public void copyText(TextArea targetTextArea, Pane pane) {
        String textToCopy = targetTextArea.getText();

        if (textToCopy.isEmpty()) return;

        ClipboardContent content = new ClipboardContent();
        content.putString(textToCopy);
        Clipboard.getSystemClipboard().setContent(content);

        Snackbar.getInstance(pane).show("Text copied to clipboard");

    }

    public void swapLanguages(TextArea sourceTextArea, TextArea targetTextArea, ComboBox<String> sourceLanguageComboBox, ComboBox<String> targetLanguageComboBox, Pane pane) {
        String sourceLanguage = sourceLanguageComboBox.getValue();
        String targetLanguage = targetLanguageComboBox.getValue();

        String sourceText = sourceTextArea.getText();
        String targetText = targetTextArea.getText();


        if (sourceLanguage.equals("Detect Language")) {
            Snackbar.getInstance(pane).show("You can't swap languages when source language is 'Detect Language'");

            return;
        }

        LanguageTranslator translator = LanguageTranslator.getInstance();

        sourceLanguageComboBox.setValue(
                translator.getSourceLanguages().stream().filter(
                        language -> language.getName().equals(targetLanguage.split(" ")[0])).findFirst().get().getName()
        );

        targetLanguageComboBox.setValue(
                translator.getTargetLanguages().stream().filter(
                        language -> language.getName().split(" ")[0].equals(sourceLanguage.split(" ")[0])).findFirst().get().getName()
        );

        sourceTextArea.setText(targetText);
        targetTextArea.setText(sourceText);
    }
}
