# 🎭 Playwright Java Automation Framework

A **Java-based UI Automation Framework** built with Playwright, Cucumber BDD, and Maven, featuring reusable test steps, JSON test data, and HTML reporting.

## ✨ Key Features

| 🌟 Feature | 📝 Description |
|:---|:---|
| 🌐 Browser Automation | UI automation with Playwright |
| 🥒 BDD Testing | Readable scenarios using Cucumber & Gherkin |
| ♻️ Reusable Steps | Modular and reusable step definitions |
| 📂 JSON Test Data | Data management using Jackson |
| 🚀 Test Execution | Centralized execution using Maven |
| 📊 HTML Reporting | Execution reports with Cucumber |

## 🛠️ Tech Stack

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Playwright-2EAD33?style=for-the-badge&logo=playwright&logoColor=white" alt="Playwright"/>
  <img src="https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white" alt="Cucumber"/>
  <img src="https://img.shields.io/badge/Gherkin-5B8C5A?style=for-the-badge" alt="Gherkin"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/TestNG-FFB000?style=for-the-badge" alt="TestNG"/>
  <img src="https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white" alt="JSON"/>
  <img src="https://img.shields.io/badge/Jackson-4B6F44?style=for-the-badge" alt="Jackson"/>
</p>

| Category | Technologies |
|:---|:---|
| 💻 Programming | Java |
| 🌐 Automation | Playwright |
| 🥒 BDD | Cucumber, Gherkin |
| ⚙️ Build & Execution | Maven, TestNG |
| 📂 Test Data | JSON, Jackson |
| 📊 Reporting | Cucumber HTML Reports |

## 🏗️ Framework Workflow

```text
       🥒 Feature Files
              ↓
       📝 Step Definitions
              ↓
     📂 JSON Test Data (Jackson)
              ↓
       🎭 Playwright Java
              ↓
       🌐 Browser Execution
              ↓
       📊 HTML Report
```

## 📸 Framework Showcase

**🌐 Browser Automation**

<p align="center">
  <img src="screenshots/browser-automation.png" width="90%" alt="Browser Automation"/>
</p>

**🥒 BDD Feature Scenarios**

<p align="center">
  <img src="screenshots/bdd-feature.png" width="90%" alt="BDD Feature Scenarios"/>
</p>

**📊 Cucumber HTML Report**

<p align="center">
  <img src="screenshots/cucumber-report.png" width="90%" alt="Cucumber HTML Report"/>
</p>

## 📂 Project Structure

```text
playwright-cucumber-java-framework/
│
├── 📁 screenshots/
│   ├── browser-automation.png
│   ├── bdd-feature.png
│   └── cucumber-report.png
│
├── 📁 src/
│   └── 📁 test/
│       ├── 📁 java/
│       │   └── 📁 com/qa/
│       │       ├── 📁 runners/
│       │       │   └── TestRunner.java
│       │       └── 📁 stepdefinitions/
│       │
│       └── 📁 resources/
│           ├── 📁 features/
│           └── 📁 testdata/
│               └── 📁 json/
│                   └── login-data.json
│
├── 📄 pom.xml
└── 📄 README.md
```

## ⚙️ Prerequisites

- ☕ JDK 21
- 📦 Apache Maven
- 🔗 Git
- 💻 Eclipse IDE (or any Java IDE)

## 🚀 Getting Started

**1. Clone the repository**

```bash
git clone https://github.com/harinisri3003/playwright-java-cucumber-framework.git
```

**2. Navigate to the project**

```bash
cd playwright-cucumber-java-framework
```

**3. Run the tests**

```bash
mvn clean test
```

*Ensure the required Playwright browser binaries are installed before execution.*

## 📊 Test Reports

Cucumber HTML report:

```text
target/cucumber-reports/cucumber.html
```

Open the generated HTML file in your browser to view the execution results.

## 📂 Test Data

JSON test data is maintained in:

```text
src/test/resources/testdata/json/
```

Jackson handles JSON data reading and processing.

---

## 👩‍💻 Let's Connect!

**Harini Sri**

<p align="left">
  <a href="https://github.com/harinisri3003">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"/>
  </a>
  <a href="https://www.linkedin.com/in/harini-sri-s/">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/>
  </a>
</p>
