# How to Use the Playwright Java Automation Framework

## 1. Prerequisites

Install the following tools:

- JDK 21
- Apache Maven
- Git
- Eclipse IDE or another Java IDE

## 2. Clone the Repository

```bash
git clone https://github.com/harinisri3003/playwright-java-cucumber-framework.git
cd playwright-cucumber-java-framework
```

## 3. Run the Tests

Open a terminal in the project root and execute:

```bash
mvn clean test
```

Maven downloads the required dependencies, compiles the project, and executes the Cucumber tests through `TestRunner.java`.

## 4. Test Scenarios

The framework uses Cucumber feature files written in Gherkin.

Current scenarios cover login and user management workflows, including:

- Login with valid credentials
- Login with invalid credentials
- Login with empty credentials
- Login with an invalid password
- Logout
- Navigation to the System Users page

## 5. Test Data

Login test data is stored in JSON format:

`src/test/resources/testdata/json/login-data.json`

Jackson is used to read the test data.

Update the JSON data carefully when changing the test scenarios or test credentials.

## 6. View the HTML Report

After the test execution completes, open:

`target/cucumber-reports/cucumber.html`

Open the file in a browser to view the Cucumber execution report.

The report is generated locally after test execution. GitHub Actions uploads it as an artifact when the report exists.

## 7. GitHub Actions

The workflow is located at:

`.github/workflows/smoke_tests.yml`

It runs automatically on pushes to `main` and pull requests targeting `main`. You can also trigger it manually from the Actions tab in GitHub.

The workflow installs Chromium, executes the tests in headless mode, and uploads the Cucumber HTML report.

## 8. Troubleshooting

**Maven command not recognized**

Install Maven and configure its `bin` directory in your system PATH.

**Browser installation issues**

Run the Playwright browser installation command configured for your project or workflow.

**Tests fail locally**

Check the Maven Console output, feature files, step definitions, test data, and browser configuration.

**HTML report not found**

Check whether the test execution completed and whether the report was generated at the expected path.