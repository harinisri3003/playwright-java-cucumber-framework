# 🎭 Playwright Java Automation Framework

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Playwright-Automation-2EAD33?style=for-the-badge&logo=playwright&logoColor=white" alt="Playwright"/>
  <img src="https://img.shields.io/badge/Cucumber-BDD-23D96C?style=for-the-badge&logo=cucumber&logoColor=white" alt="Cucumber"/>
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
</p>

A Java-based UI test automation framework built using **Playwright, Cucumber BDD, and Maven**. Designed to support reusable browser automation, behavior-driven scenarios, JSON-based test data management, and automated test reporting.

---

## ✨ Key Features

| Feature | Description |
|---|---|
| 🌐 Browser Automation | UI automation using Playwright with Java |
| 🥒 BDD Framework | Readable test scenarios using Cucumber and Gherkin |
| ♻️ Reusability | Shared setup and reusable step definitions |
| 📂 Test Data | JSON-based test data management using Jackson |
| 🚀 Test Execution | Centralized execution through Cucumber TestRunner |
| 📊 Reporting | Cucumber HTML execution reports |

---

## 🔄 Framework Workflow

```mermaid
flowchart TD
    A["🥒 Feature Files<br/>Gherkin Scenarios"] --> B["⚙️ Step Definitions"]
    C["📂 JSON Test Data<br/>Jackson"] --> B
    B --> D["🎭 Playwright Java"]
    D --> E["🌐 Browser Execution"]
    E --> F["📊 Cucumber HTML Report"]
```

---

## 📸 Framework Showcase

### 🌐 Browser Automation

<p align="center">
  <img src="screenshots/browser-automation.png" width="850" alt="Playwright Browser Automation"/>
</p>

### 🥒 BDD Feature Scenarios

<p align="center">
  <img src="screenshots/bdd-feature.png" width="850" alt="Cucumber BDD Feature Scenarios"/>
</p>

### 📊 Cucumber HTML Report

<p align="center">
  <img src="screenshots/cucumber-report.png" width="850" alt="Cucumber HTML Execution Report"/>
</p>

---

## 🛠️ Technology Stack

**Programming Language**

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)

**Browser Automation & BDD**

![Playwright](https://img.shields.io/badge/Playwright-2EAD33?style=flat-square&logo=playwright&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=flat-square&logo=cucumber&logoColor=white)
![Gherkin](https://img.shields.io/badge/Gherkin-5BAF50?style=flat-square)

**Test Execution & Data Management**

![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF6C37?style=flat-square)
![JSON](https://img.shields.io/badge/JSON-000000?style=flat-square&logo=json&logoColor=white)
![Jackson](https://img.shields.io/badge/Jackson-4051B5?style=flat-square)

**Reporting**

![Cucumber HTML Report](https://img.shields.io/badge/Cucumber-HTML%20Report-23D96C?style=flat-square)

---

## 📁 Project Structure

```text
playwright-java-cucumber-framework/
│
├── screenshots/
│   ├── browser-automation.png
│   ├── bdd-feature.png
│   └── cucumber-report.png
│
├── src/
│   └── test/
│       ├── java/
│       │   └── com/qa/
│       │       ├── runners/
│       │       │   └── TestRunner.java
│       │       ├── stepdefinitions/
│       │       └── ...
│       │
│       └── resources/
│           ├── features/
│           └── testdata/
│               └── json/
│                   └── login-data.json
│
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- JDK 21
- Apache Maven
- Git
- Eclipse IDE or any Java IDE

### Installation

**1. Clone the repository**

```bash
git clone https://github.com/harinisri3003/playwright-java-cucumber-framework.git
```

**2. Navigate to the project directory**

```bash
cd playwright-java-cucumber-framework
```

**3. Execute the automation tests**

```bash
mvn clean test
```

---

## 📊 Test Reports

Cucumber HTML reports are generated after test execution.

**Report Location**

```text
target/cucumber-reports/cucumber.html
```

Open the generated HTML file in a browser to view scenario execution results and test details.

---

## 📂 Test Data Management

Test data is maintained separately in JSON files.

```text
src/test/resources/testdata/json/
```

**Jackson** is used to read and manage JSON-based test data.

---

## 👩‍💻 Author

**Harini Sri**

[![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/harinisri3003)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=flat-square&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/harini-sri-s/)

---

<p align="center">
  <b>✨ Automate. Test. Improve. Repeat. 🚀</b>
</p>
