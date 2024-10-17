package com.example.langt.model;

import java.util.UUID;

public class Sentence {


    private String id;
    private String source_text;
    private String target_text;
    private String source_lang;
    private String target_lang;


    public Sentence( String source_text, String source_lang,String target_text, String target_lang) {
        this.id = UUID.randomUUID().toString();
        this.source_text = source_text;
        this.target_text = target_text;
        this.source_lang = source_lang;
        this.target_lang = target_lang;

    }

    public String getId() {
        return id;
    }

    public String getSource_text() {
        return source_text;
    }

    public void setSource_text(String source_text) {
        this.source_text = source_text;
    }

    public String getTarget_text() {
        return target_text;
    }

    public void setTarget_text(String target_text) {
        this.target_text = target_text;
    }

    public String getSource_lang() {
        return source_lang;
    }

    public void setSource_lang(String source_lang) {
        this.source_lang = source_lang;
    }

    public String getTarget_lang() {
        return target_lang;
    }

    public void setTarget_lang(String target_lang) {
        this.target_lang = target_lang;
    }
}
