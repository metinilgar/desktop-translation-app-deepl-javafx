package com.example.langt.sqlite;

import com.example.langt.model.Sentence;

import java.sql.Connection;
import java.util.List;

public interface ISQLiteJDBC {
    Connection connect();
    void createNewTable();
    void insert(Sentence sentence);
    List<Sentence> getTranslations();
    void deleteAll();

    static ISQLiteJDBC getInstance() {
        return null;
    }
}