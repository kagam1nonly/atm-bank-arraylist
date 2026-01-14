# ATM Bank System

A modern ATM banking application built with Java 21 and Swing UI.

## Features

- User registration and authentication
- Account balance management
- Deposit and withdrawal operations
- Modern dark UI with FlatLaf
- JSON-based data persistence

## Technologies

- **Java**: 21 (LTS)
- **Build Tool**: Maven
- **UI Framework**: Swing with FlatLaf
- **Data Storage**: JSON (Gson)

## Project Structure

```
java-atm-bank/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── kagami/
│                   └── atm/
│                       ├── ATMService.java
│                       ├── User.java
│                       └── ui/
│                           ├── ATMFrame.java
│                           ├── LoginPanel.java
│                           ├── RegisterPanel.java
│                           └── DashboardPanel.java
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Building the Project

```bash
mvn clean compile
```

### Running the Application

```bash
mvn exec:java -Dexec.mainClass="com.kagami.atm.ui.ATMFrame"
```

Or use the provided batch file:
```bash
run_atm.bat
```

## Screenshots

_Screenshots will be added here_

## License

This project is for educational purposes.
