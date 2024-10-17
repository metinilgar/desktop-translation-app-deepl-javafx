package com.example.langt;

import com.example.langt.sqlite.SQLiteJDBC;
import com.example.langt.viewmodel.SplashScreenController;
import javafx.application.Application;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.util.Objects;


public class LangTApplication extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SQLiteJDBC.getInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SplashScreen.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("LangT");
        primaryStage.setResizable(false);

        primaryStage.show();

        SplashScreenController controller = fxmlLoader.getController();
        controller.checkInternetConnection(() -> Platform.runLater(() -> loadMainApplication(primaryStage)));


    }


    private void loadMainApplication(Stage splashStage) {

        splashStage.close();
        try {
            Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TranslationView.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(LangTApplication.class.getResource("css/styles.css")).toExternalForm());

            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);

            primaryStage.setTitle("LangT");
            primaryStage.setResizable(false);

            primaryStage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
