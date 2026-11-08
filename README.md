# 🎭 Playwright Java Automation Framework

A UI test automation framework built using **Java, Playwright, Cucumber BDD, and Maven**. This framework follows the Behavior-Driven Development (BDD) approach, supports reusable test steps, and uses JSON-based test data management for maintainable and scalable automation.

## ✨ Key Features

- **Browser Automation:** Automates web application interactions using Playwright with Java.
- **BDD Framework:** Uses Cucumber and Gherkin to write readable, business-focused test scenarios.
- **Reusable Step Definitions:** Implements reusable steps to reduce code duplication and improve maintainability.
- **JSON Test Data Management:** Manages test data using JSON files and Jackson.
- **Centralized Test Execution:** Executes scenarios through a Cucumber TestRunner.
- **HTML Reporting:** Generates Cucumber HTML reports to review test execution results.

## 🛠️ Technology Stack

| Category | Technologies |
|:---|:---|
| **Programming Language** | Java |
| **Browser Automation** | Playwright |
| **BDD Framework** | Cucumber, Gherkin |
| **Build & Test Execution** | Maven, TestNG |
| **Test Data Management** | JSON, Jackson |
| **Test Reporting** | Cucumber HTML Reports |

## 🏗️ Framework Architecture

```text
              Feature Files
             (Gherkin Scenarios)
                     |
                     v
              Step Definitions
                     |
           +---------+---------+
           |                   |
           v                   v
      JSON Test Data      Reusable Methods
        (Jackson)               |
           |                   |
           +---------+---------+
                     |
                     v
              Playwright Java
                     |
                     v
              Browser Execution
                     |
                     v
             Cucumber HTML Report
```

## 📸 Framework Showcase

### 🌐 Browser Automation

<p align="center">
  <img src="screenshots/browser-automation.png" width="90%" alt="Playwright Browser Automation"/>
</p>

### 🥒 BDD Feature Scenarios

<p align="center">
  <img src="screenshots/bdd-feature.png" width="90%" alt="Cucumber BDD Feature Scenarios"/>
</p>

### 📊 Cucumber HTML Report

<p align="center">
  <img src="screenshots/cucumber-report.png" width="90%" alt="Cucumber HTML Execution Report"/>
</p>

## 📂 Project Structure

```text
playwright-cucumber-java-framework/
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
│       │       └── stepdefinitions/
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

## ⚙️ Prerequisites

Make sure the following tools are installed:

- JDK 21
- Apache Maven
- Git
- Eclipse IDE or another Java IDE

## 🚀 Getting Started

**1. Clone the repository**

```bash
git clone https://github.com/harinisri3003/playwright-java-cucumber-framework.git
```

**2. Navigate to the project directory**

```bash
cd playwright-cucumber-java-framework
```

**3. Execute the tests**

```bash
mvn clean test
```

Ensure the required Playwright browser binaries are installed before running the tests.

## 📊 Test Reports

After test execution, the Cucumber HTML report is generated at:

```text
target/cucumber-reports/cucumber.html
```

Open the generated HTML file in a browser to review the test execution results.

## 📁 Test Data Management

Test data is maintained in JSON files under:

```text
src/test/resources/testdata/json/
```

Jackson is used to read and process JSON test data, keeping test data separate from automation logic.

## 👩‍💻 Author

**Harini Sri**

- **GitHub:** [harinisri3003](https://github.com/harinisri3003)
- **LinkedIn:** [Connect with me](https://www.linkedin.com/in/harini-sri-s/)
