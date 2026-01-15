# Modern ATM Banking System with a simple UI

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

<img width="1103" height="738" alt="image" src="https://github.com/user-attachments/assets/b755402c-6041-4d2e-996a-8e60fdb132ab" />
<img width="1101" height="735" alt="image" src="https://github.com/user-attachments/assets/61f975a4-c69f-4a4b-a506-615efeabcf25" />
<img width="1104" height="737" alt="image" src="https://github.com/user-attachments/assets/4bdd3e6e-6a0c-4619-9313-1f76a7b4eb0d" />
<img width="1103" height="737" alt="image" src="https://github.com/user-attachments/assets/26b928f0-4b4b-4402-9f80-05a0fbfbc8b4" />



## License

This project is for educational purposes.


