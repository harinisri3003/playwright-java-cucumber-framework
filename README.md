
# Playwright Java Automation Framework

A UI test automation framework built using Java, Playwright, Cucumber BDD, and Maven. The framework supports behavior-driven test scenarios, JSON-based test data management, automated test execution, and CI integration through GitHub Actions.

## Tech Stack

- **Language:** Java
- **Browser Automation:** Playwright
- **BDD:** Cucumber, Gherkin
- **Test Execution:** TestNG, Maven
- **Test Data:** JSON, Jackson
- **CI/CD:** GitHub Actions
- **Reporting:** Cucumber HTML Report

## Key Features

- Browser UI automation using Playwright with Java.
- BDD scenarios written in Gherkin feature files.
- Reusable step definitions and shared test setup.
- JSON-based test data management using Jackson.
- Maven-based test execution through a single Cucumber `TestRunner`.
- GitHub Actions workflow for automated test execution.
- Cucumber HTML report generated after test execution.

## Project Structure

```text
playwright-cucumber-java-framework/
├── .github/
│   └── workflows/
│       └── smoke_tests.yml
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com/qa/
│   │   │       ├── runners/
│   │   │       │   └── TestRunner.java
│   │   │       ├── stepdefinitions/
│   │   │       └── ...
│   │   └── resources/
│   │       ├── features/
│   │       └── testdata/
│   │           └── json/
│   │               └── login-data.json
├── pom.xml
└── README.md
```

## Prerequisites

- JDK 21
- Apache Maven
- Git
- Eclipse IDE or another Java IDE

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/harinisri3003/playwright-java-cucumber-framework.git
   ```

2. Navigate to the project directory:

   ```bash
   cd playwright-cucumber-java-framework
   ```

3. Install dependencies and run the tests:

   ```bash
   mvn clean test
   ```

The GitHub Actions workflow installs Chromium and executes the tests in headless mode.

## Test Reports

After execution, the Cucumber HTML report is available at:

`target/cucumber-reports/cucumber.html`

To view it, open the HTML file in a web browser.

GitHub Actions also uploads the report as a workflow artifact when it is generated.

## Test Data

Test data is maintained in JSON files under:

`src/test/resources/testdata/json/`

Jackson is used to read the JSON test data.

## CI Workflow

The GitHub Actions workflow runs on pushes to the `main` branch, pull requests targeting `main`, and manual triggers.

## Author

**Harini Sri**

GitHub: [harinisri3003](https://github.com/harinisri3003)
LinkedIn: [harinisri3003](https://www.linkedin.com/in/harini-sri-s/)

