package com.example.langt.viewmodel.Translator;

import com.deepl.api.*;
import com.example.langt.model.Sentence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



import java.util.List;

public class LanguageTranslator implements ILanguageTranslator{
    private static LanguageTranslator instance;

    private final Translator translator;
    private final List<Language> targetLanguages;
    private final List<Language> sourceLanguages;

    private LanguageTranslator() throws DeepLException, InterruptedException {
        String apiKey = loadApiKey();
        translator = new Translator(apiKey);

        this.sourceLanguages = translator.getSourceLanguages();
        this.targetLanguages = translator.getTargetLanguages();

    }

    private String loadApiKey() {
        Properties properties = new Properties();
        String apiKey = null;
        try (InputStream input = LanguageTranslator.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }
            properties.load(input);
            apiKey = properties.getProperty("deepl.api.key");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return apiKey;
    }

    public Sentence translateText(String source_text, String sourceLanguage, String targetLanguage) throws DeepLException, InterruptedException {
        String targetLanguageCode = null;
        String sourceLanguageCode = null;

        // dil kodlarını al
        targetLanguageCode = targetLanguages.stream().filter(language -> language.getName().equals(targetLanguage)).findFirst().get().getCode();
        if (!sourceLanguage.equals("Detect Language")) {
            String finalSourceLanguage = sourceLanguage;
            sourceLanguageCode = sourceLanguages.stream().filter(language -> language.getName().equals(finalSourceLanguage)).findFirst().get().getCode();
        }

        // dil kodlarına göre çeviri yap
        TextResult result =
                translator.translateText(source_text, sourceLanguageCode, targetLanguageCode);

        // eğer kaynak dil otomatik algılamada ise kaynak dilin koduna göre kayank dili bul
        if (sourceLanguage.equals("Detect Language")){
            sourceLanguageCode = result.getDetectedSourceLanguage();
            String finalSourceLanguageCode = sourceLanguageCode;
            sourceLanguage = sourceLanguages.stream().filter(language -> language.getCode().equals(finalSourceLanguageCode)).findFirst().get().getName();

        }

        return new Sentence(source_text, sourceLanguage, result.getText(), targetLanguage);

    }

    public List<Language> getTargetLanguages() {
        return targetLanguages;
    }

    public List<Language> getSourceLanguages() {
        return sourceLanguages;
    }

    public static LanguageTranslator getInstance() {
        if (instance == null){
            try {
                instance = new LanguageTranslator();
            } catch (DeepLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

}
