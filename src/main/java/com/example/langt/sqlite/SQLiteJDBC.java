package com.example.langt.sqlite;

import com.example.langt.model.Sentence;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Properties;


public class SQLiteJDBC implements ISQLiteJDBC{
   private static final String url;
   private static SQLiteJDBC instance;


    static {
        String tempUrl = null;
        Properties properties = new Properties();
        try (InputStream input = SQLiteJDBC.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            } else {
                properties.load(input);
                tempUrl = "jdbc:sqlite:" + properties.getProperty("db.path");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        url = tempUrl;
    }



   private SQLiteJDBC() {
       createNewTable();
    }

    // Veritabanı bağlantısını açan metot
    public Connection connect() {

        Connection conn = null;
        try {
            // Bağlantıyı oluştur
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS translations (\n"
                + " id TEXT PRIMARY KEY,\n"
                + " source_text TEXT NOT NULL,\n"
                + " source_lang TEXT NOT NULL,\n"
                + " target_text TEXT NOT NULL,\n"
                + " target_lang TEXT NOT NULL\n"
                + ");";


        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Tabloya veri ekleyen metot
    public void insert(Sentence sentence) {
        String sql = "INSERT INTO translations(id, source_text, source_lang, target_text, target_lang) VALUES(?, ?, ?, ?, ?)";

        try( Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sentence.getId());
            pstmt.setString(2, sentence.getSource_text());
            pstmt.setString(3, sentence.getSource_lang());
            pstmt.setString(4, sentence.getTarget_text());
            pstmt.setString(5, sentence.getTarget_lang());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Veritabanından veri okuyan metot
    public  List<Sentence>  getTranslations() {
        List<Sentence> translations = new ArrayList<>();
        String sql = "SELECT source_text, source_lang, target_text, target_lang FROM translations";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // Sonuçları döngüyle oku
            while (rs.next()) {
                String sourceText = rs.getString("source_text");
                String sourceLang = rs.getString("source_lang");
                String targetText = rs.getString("target_text");
                String targetLang = rs.getString("target_lang");
                translations.add(new Sentence(sourceText, sourceLang, targetText, targetLang));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return translations.reversed();
    }

    public void deleteAll() {
        String sql = "DELETE FROM translations";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static SQLiteJDBC getInstance() {
        if (instance == null){
            instance = new SQLiteJDBC();
        }
        return instance;
    }

}
