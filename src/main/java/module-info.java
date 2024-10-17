module com.example.langt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires deepl.java;
    requires java.sql;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires MaterialFX;
    requires ch.qos.logback.core;


    exports com.example.langt;
    opens com.example.langt to javafx.fxml;
    exports com.example.langt.viewmodel.Translator;
    opens com.example.langt.viewmodel.Translator to javafx.fxml;
    exports com.example.langt.viewmodel;
    opens com.example.langt.viewmodel to javafx.fxml;
}