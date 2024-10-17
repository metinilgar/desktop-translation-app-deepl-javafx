package com.example.langt.viewmodel.Commands;



import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class ButtonClickedReceiverTest extends ApplicationTest {

    private ButtonClickedReceiver buttonClickedReceiver;
    private TextArea sourceTextArea;
    private TextArea targetTextArea;
    private ComboBox<String> sourceLanguageComboBox;
    private ComboBox<String> targetLanguageComboBox;
    private Pane pane;


    @BeforeEach
    void setUp() {
        buttonClickedReceiver = new ButtonClickedReceiver();
        sourceTextArea = new TextArea();
        targetTextArea = new TextArea();
        sourceLanguageComboBox = new ComboBox<>();
        targetLanguageComboBox = new ComboBox<>();
        pane = new Pane();
    }


    @Test
    void deleteTextArea() {
        sourceTextArea.setText("Source text");
        targetTextArea.setText("Target text");

        buttonClickedReceiver.deleteTextArea(sourceTextArea, targetTextArea);

        assertTrue(sourceTextArea.getText().isEmpty());
        assertTrue(targetTextArea.getText().isEmpty());
    }

    @Test
    void swapLanguages() {
        sourceLanguageComboBox.getItems().addAll("English", "Spanish");
        targetLanguageComboBox.getItems().addAll("English", "Spanish");

        sourceLanguageComboBox.setValue("English");
        targetLanguageComboBox.setValue("Spanish");

        sourceTextArea.setText("Hello");
        targetTextArea.setText("Hola");

        buttonClickedReceiver.swapLanguages(sourceTextArea, targetTextArea, sourceLanguageComboBox, targetLanguageComboBox, pane);

        assertEquals("Hola", sourceTextArea.getText());
        assertEquals("Hello", targetTextArea.getText());
        assertEquals("Spanish", sourceLanguageComboBox.getValue());
        assertEquals("English (British)", targetLanguageComboBox.getValue());
    }
}