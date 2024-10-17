package com.example.langt.sqlite;

import com.example.langt.model.Sentence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteJDBCTest {

    private SQLiteJDBC sqlitejdbc;
    private Sentence sentence;
    private List<Sentence> translationsBackup;

    // Test başında veritabanındaki tüm kayıtlar yedeklenmeli ve veritabanı temizlenmeli.
    @BeforeEach
    void setUp() {
        sqlitejdbc = SQLiteJDBC.getInstance();
        sentence = new Sentence("Hello", "en", "Hola", "es");


        translationsBackup = sqlitejdbc.getTranslations();
        sqlitejdbc.deleteAll();
    }

    // Insert metodu çağrıldığı zaman veritabanına yeni bir cümle eklenmeli.
    @Test
    void shouldInsertSentenceWhenInsertCalled() {
        sqlitejdbc.insert(sentence);
        List<Sentence> translations = sqlitejdbc.getTranslations();
        Sentence insertedSentence = translations.get(translations.size() - 1);
        assertEquals(sentence.getSource_text(), insertedSentence.getSource_text());
        assertEquals(sentence.getSource_lang(), insertedSentence.getSource_lang());
        assertEquals(sentence.getTarget_text(), insertedSentence.getTarget_text());
        assertEquals(sentence.getTarget_lang(), insertedSentence.getTarget_lang());
    }

    // Connect metodu çağrıldığı zaman veritabanına bağlantı sağlanmalı.
    @Test
    void shouldConnectToDatabaseWhenConnectCalled() {
        Connection conn = sqlitejdbc.connect();
        assertNotNull(conn);
    }

    // CreateNewTable metodu çağrıldığı zaman yeni bir tablo oluşturulmalı.
    @Test
    void shouldCreateNewTableWhenCreateNewTableCalled() {
        sqlitejdbc.createNewTable();
    }

    // GetTranslations metodu çağrıldığı zaman veritabanındaki tüm çevirileri dönmeli.
    @Test
    void shouldGetTranslationsWhenGetTranslationsCalled() {
        List<Sentence> translations = sqlitejdbc.getTranslations();
        assertNotNull(translations);
    }

    // DeleteAll metodu çağrıldığı zaman veritabanındaki tüm kayıtlar silinmeli.
    @Test
    void shouldDeleteAllRecordsWhenDeleteAllCalled() {
        sqlitejdbc.deleteAll();
        List<Sentence> translations = sqlitejdbc.getTranslations();
        assertTrue(translations.isEmpty());
    }

    // Delete metodu çağrıldığı zaman veritabanındaki belirtilen kayıt silinmeli.
    @Test
    void shouldReturnInstanceWhenGetInstanceCalled() {
        SQLiteJDBC instance = SQLiteJDBC.getInstance();
        assertNotNull(instance);
    }

    // Test sonunda veritabanındaki tüm kayıtlar silinmeli ve yedeklenen kayıtlar geri yüklenmeli.
    @AfterEach
    void tearDown() {
        sqlitejdbc.deleteAll();
        for (int i = 0; i < translationsBackup.size(); i++) {
            sqlitejdbc.insert(translationsBackup.get(i));
        }
    }
}