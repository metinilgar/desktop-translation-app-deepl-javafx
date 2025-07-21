# LangT - Masaüstü Çeviri Uygulaması

JavaFX ve DeepL API kullanılarak geliştirilmiş modern bir masaüstü çeviri uygulaması.

![LangT Uygulaması](Project%20Reports/app.png)

## 🚀 Özellikler

- **Hızlı Çeviri**: DeepL API entegrasyonu ile yüksek kaliteli çeviriler
- **Çeviri Geçmişi**: SQLite veritabanı ile çeviri geçmişinizi saklama
- **Modern Arayüz**: JavaFX ile tasarlanmış kullanıcı dostu arayüz
- **Dil Değiştirme**: Kaynak ve hedef dilleri kolayca değiştirme
- **Metin Kopyalama**: Çeviri sonuçlarını tek tıkla kopyalama
- **Splash Screen**: Uygulama başlatma animasyonu

## 🛠️ Teknolojiler

- **Java 11+**
- **JavaFX**: Modern UI framework
- **SQLite**: Yerel veritabanı
- **DeepL API**: Çeviri servisi
- **Maven**: Proje yönetimi

## 📁 Proje Yapısı

```
src/
├── main/
│   ├── java/
│   │   └── com/example/langt/
│   │       ├── model/          # Veri modelleri
│   │       ├── viewmodel/      # UI kontrolcüleri
│   │       └── sqlite/         # Veritabanı işlemleri
│   └── resources/
│       └── com/example/langt/
│           ├── css/            # Stil dosyaları
│           ├── image/          # Görseller
│           └── *.fxml          # UI tanımları
```

## 🏗️ Mimari Diyagramları

### UML Sınıf Diyagramı
![UML Diyagramı](Project%20Reports/UML%20diagram.png)

### Veritabanı İlişki Diyagramı
![Veritabanı İlişki Diyagramı](Project%20Reports/Relationship%20Diagram.png)

## 🏃‍♂️ Nasıl Çalıştırılır

1. Depoyu klonlayın:
   ```bash
   git clone [repository-url]
   cd LangT
   ```

2. Maven ile derleyin:
   ```bash
   mvn clean compile
   ```

3. Uygulamayı çalıştırın:
   ```bash
   mvn javafx:run
   ```

## ⚙️ Yapılandırma

- DeepL API anahtarınızı `config.properties` dosyasına ekleyin
- Desteklenen diller otomatik olarak yüklenecektir

## 📄 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için `LICENSE` dosyasına bakın.