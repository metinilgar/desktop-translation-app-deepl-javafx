# LangT - Desktop Translation Application

A modern desktop translation application developed using JavaFX and DeepL API.

![LangT Application](Project%20Reports/app.png)

## 🚀 Features

- **Fast Translation**: High-quality translations with DeepL API integration
- **Translation History**: Store your translation history with SQLite database
- **Modern Interface**: User-friendly interface designed with JavaFX
- **Language Switching**: Easily switch between source and target languages
- **Text Copying**: Copy translation results with a single click
- **Splash Screen**: Application startup animation

## 🛠️ Technologies

- **Java 11+**
- **JavaFX**: Modern UI framework
- **SQLite**: Local database
- **DeepL API**: Translation service
- **Maven**: Project management

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/langt/
│   │       ├── model/          # Data models
│   │       ├── viewmodel/      # UI controllers
│   │       └── sqlite/         # Database operations
│   └── resources/
│       └── com/example/langt/
│           ├── css/            # Style files
│           ├── image/          # Images
│           └── *.fxml          # UI definitions
```

## 🏗️ Architecture Diagrams

### UML Class Diagram
![UML Diagram](Project%20Reports/UML%20diagram.png)

### Database Relationship Diagram
![Database Relationship Diagram](Project%20Reports/Relationship%20Diagram.png)

## 🏃‍♂️ How to Run

1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd LangT
   ```

2. Build with Maven:
   ```bash
   mvn clean compile
   ```

3. Run the application:
   ```bash
   mvn javafx:run
   ```

## ⚙️ Configuration

- Add your DeepL API key to the `config.properties` file
- Supported languages will be loaded automatically

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details. 