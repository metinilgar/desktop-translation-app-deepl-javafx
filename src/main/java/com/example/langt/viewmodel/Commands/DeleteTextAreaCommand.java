package com.example.langt.viewmodel.Commands;


import javafx.scene.control.TextArea;

public class DeleteTextAreaCommand implements ICommand{
    private final ButtonClickedReceiver receiver;

    private final TextArea sourceTextArea;
    private final TextArea targetTextArea;

    public DeleteTextAreaCommand(ButtonClickedReceiver receiver, TextArea sourceTextArea, TextArea targetTextArea) {
        this.receiver = receiver;

        this.sourceTextArea = sourceTextArea;
        this.targetTextArea = targetTextArea;
    }

    @Override
    public void execute() {
        receiver.deleteTextArea(sourceTextArea,targetTextArea);
    }
}
