package com.example.langt.viewmodel;

import com.example.langt.model.HistoryTranslationListCell;
import com.example.langt.model.Sentence;
import com.example.langt.sqlite.SQLiteJDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HistoryTranslationsScreenController implements Initializable {


    @FXML
    private ListView<Sentence> historyList;

    @FXML
    private Button closePage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Sentence> translations = SQLiteJDBC.getInstance().getTranslations();

        historyList.getItems().clear();
        historyList.getItems().addAll(translations);
        historyList.setCellFactory(param -> new HistoryTranslationListCell());
    }

    @FXML
    public void switchToTranslationView(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/langt/TranslationView.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteAll(){
        SQLiteJDBC.getInstance().deleteAll();
        historyList.getItems().clear();
    }


}
