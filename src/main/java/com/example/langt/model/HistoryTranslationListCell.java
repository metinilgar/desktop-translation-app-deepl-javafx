package com.example.langt.model;

import com.example.langt.viewmodel.TranslationViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class HistoryTranslationListCell extends ListCell<Sentence>  {
    private final VBox vbox;
    private final Label line1;
    private final Label line2;
    private final Label line3;
    private final Button editButton;
    private final HBox hbox;


    public HistoryTranslationListCell() {
        super();
        line1 = new Label();
        line2 = new Label();
        line3 = new Label();
        editButton = new Button();

        // FontAwesome ikonu ekle
        FontIcon editIcon = new FontIcon("bi-pencil-square");
        editButton.setGraphic(editIcon);

        // Stil özelliklerini burada tanımlayabilirsiniz
        line1.getStyleClass().add("cell-line1");
        line2.getStyleClass().add("cell-line2");
        line3.getStyleClass().add("cell-line3");
        editButton.getStyleClass().add("edit-button");

        vbox = new VBox(line1, line2, line3);


        hbox = new HBox(vbox, editButton);
        HBox.setHgrow(vbox, Priority.ALWAYS);
        hbox.setAlignment(Pos.CENTER);


        editButton.setOnAction(event -> {
            Sentence currentItem = getItem();
            if (currentItem != null) {
                try {
                    openEditWindow(event,currentItem);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    protected void updateItem(Sentence item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            line1.setText(item.getSource_lang() +  "  -->  "+item.getTarget_lang());
            line2.setText(item.getSource_text());
            line3.setText(item.getTarget_text());
            setGraphic(hbox);
        }
    }

    private void openEditWindow(ActionEvent event,Sentence item) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/langt/TranslationView.fxml"));
        Parent root = loader.load();

        TranslationViewController controller = loader.getController();
        controller.edit(item);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
