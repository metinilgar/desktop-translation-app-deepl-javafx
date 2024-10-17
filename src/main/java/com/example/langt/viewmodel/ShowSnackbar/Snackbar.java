package com.example.langt.viewmodel.ShowSnackbar;

import com.example.langt.LangTApplication;
import com.jfoenix.controls.JFXSnackbarLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Snackbar {
    private static Snackbar instance;

    private final JFXSnackbar JFXsnackbar;


    private Snackbar(Pane pane) {
        // JFXSnackbar oluşturun
        JFXsnackbar = new JFXSnackbar(pane);
        JFXsnackbar.getStylesheets().add(
                LangTApplication.class.getResource("css/snackbar.css").toExternalForm()
        );
    }

    public void show(String message) {
        // Mesaj içeriğini oluşturun
        JFXSnackbarLayout snackbarLayout = new JFXSnackbarLayout(message);

        JFXsnackbar.close();
        // Snackbar'ı gösterin
        JFXsnackbar.fireEvent(new JFXSnackbar.SnackbarEvent(snackbarLayout, Duration.seconds(3), null));

    }

    public void show(String message, String actionMessage, EventHandler<ActionEvent> action) {
        JFXSnackbarLayout snackbarLayout = new JFXSnackbarLayout(message, actionMessage, actionEvent -> {
            action.handle(actionEvent);
            JFXsnackbar.close();

        });
        JFXsnackbar.fireEvent(new JFXSnackbar.SnackbarEvent(snackbarLayout, Duration.INDEFINITE, null));
    }

    public void updatePane(Pane pane) {
        JFXsnackbar.unregisterSnackbarContainer(this.JFXsnackbar.getPopupContainer());
        this.JFXsnackbar.registerSnackbarContainer(pane);
    }

    public static Snackbar getInstance(Pane pane) {
        if (instance == null) {
            instance = new Snackbar(pane);
        }
        return instance;
    }
}
