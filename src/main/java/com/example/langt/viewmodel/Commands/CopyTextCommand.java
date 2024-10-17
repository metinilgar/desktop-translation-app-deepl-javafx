package com.example.langt.viewmodel.Commands;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class CopyTextCommand implements ICommand{
    private final ButtonClickedReceiver receiver;

    private final TextArea targetTextArea;
    private final Pane pane;

    public CopyTextCommand(ButtonClickedReceiver receiver, TextArea targetTextArea, Pane pane){
        this.receiver = receiver;
        this.targetTextArea = targetTextArea;
        this.pane = pane;

    }

    @Override
    public void execute() {
        receiver.copyText(targetTextArea, pane);
    }
}
