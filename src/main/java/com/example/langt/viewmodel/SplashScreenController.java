package com.example.langt.viewmodel;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashScreenController {
    private static final int CHECK_INTERVAL = 1000; // 1 seconds

    @FXML
    private Label countdown;

    public void checkInternetConnection(Runnable onConnectionAvailable) {
        new Thread(() -> {
            while (true) {
                if (isInternetAvailable()) {
                    Platform.runLater(() -> countdown.setText("Connecting..."));
                    try {
                        Thread.sleep(CHECK_INTERVAL);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    onConnectionAvailable.run();
                    break;
                } else {

                    for (int i = 10; i > 0; i--) {
                        int finalI = i;
                        Platform.runLater(() -> countdown.setText( finalI + " seconds\nto retry the connection"));
                        try {
                            Thread.sleep(CHECK_INTERVAL); // 1 seconds

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    
                    

                }
            }
        }).start();
    }

    private boolean isInternetAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            return false;
        }
    }
}
