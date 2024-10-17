package com.example.langt.viewmodel.Translator;

import com.deepl.api.*;
import com.example.langt.model.Sentence;

import java.util.List;

public interface ILanguageTranslator {
    Sentence translateText(String source_text, String sourceLanguage, String targetLanguage) throws DeepLException, InterruptedException;
    List<Language> getTargetLanguages();
    List<Language> getSourceLanguages();
    static ILanguageTranslator getInstance() {
        return null;
    }
}