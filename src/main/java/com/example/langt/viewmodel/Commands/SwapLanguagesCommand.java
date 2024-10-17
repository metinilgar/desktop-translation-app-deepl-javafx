package com.example.langt.viewmodel.Commands;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class SwapLanguagesCommand implements ICommand{
    private final ButtonClickedReceiver receiver;

    private final TextArea sourceTextArea;
    private final TextArea targetTextArea;
    private final ComboBox<String> sourceLanguageComboBox;
    private final ComboBox<String> targetLanguageComboBox;
    private final Pane pane;

    public SwapLanguagesCommand(ButtonClickedReceiver receiver,
                                TextArea sourceTextArea,
                                TextArea targetTextArea,
                                ComboBox<String> sourceLanguageComboBox,
                                ComboBox<String> targetLanguageComboBox,
                                Pane pane) {
        this.receiver = receiver;
        this.sourceTextArea = sourceTextArea;
        this.targetTextArea = targetTextArea;
        this.sourceLanguageComboBox = sourceLanguageComboBox;
        this.targetLanguageComboBox = targetLanguageComboBox;
        this.pane = pane;
    }

    @Override
    public void execute() {
            receiver.swapLanguages(sourceTextArea, targetTextArea, sourceLanguageComboBox, targetLanguageComboBox, pane);
    }
}
