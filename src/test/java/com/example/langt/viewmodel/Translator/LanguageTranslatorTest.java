package com.example.langt.viewmodel.Translator;


import com.deepl.api.DeepLException;
import com.deepl.api.Language;
import com.example.langt.model.Sentence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LanguageTranslatorTest {

    private LanguageTranslator languageTranslator;

    @BeforeEach
    void setUp() throws DeepLException, InterruptedException {
        languageTranslator = LanguageTranslator.getInstance();
    }

    @Test
    void shouldTranslateTextSuccessfully() throws DeepLException, InterruptedException {
        String sourceText = "Hello";
        String sourceLanguage = "English";
        String targetLanguage = "Spanish";
        String targetText = "Hola";
        Sentence result = languageTranslator.translateText(sourceText, sourceLanguage, targetLanguage);
        assertEquals(sourceText, result.getSource_text());
        assertEquals(sourceLanguage, result.getSource_lang());
        assertEquals(targetLanguage, result.getTarget_lang());
        assertEquals(targetText, result.getTarget_text());
    }


    @Test
    void shouldReturnSourceLanguages() {
        List<Language> result = languageTranslator.getSourceLanguages();
        assertNotNull(result);
    }

    @Test
    void shouldReturnTargetLanguages() {
        List<Language> result = languageTranslator.getTargetLanguages();
        assertNotNull(result);
    }

    @Test
    void shouldReturnInstanceWhenGetInstanceCalled() {
        LanguageTranslator instance = LanguageTranslator.getInstance();
        assertNotNull(instance);
    }
}