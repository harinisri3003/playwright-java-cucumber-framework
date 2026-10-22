package com.qa.stepdefinitions;

import com.qa.contexts.TestContext;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.testutilities.TestProofsCollection;
import com.qa.testutilities.stepStatus;
import com.qa.utils.JsonDataManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Map;

public class LoginSteps {

    private final TestContext testContext;
    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;

    private static final Logger logger =
            LoggerFactory.getLogger(LoginSteps.class);

    private final TestProofsCollection tc;

    // Dependency injection using PicoContainer
    public LoginSteps(TestContext testContext) {
        this.testContext = testContext;

        // Initialize page objects using the shared ElementUtils
        this.loginPage = new LoginPage(testContext.getElementUtils());
        this.dashboardPage = new DashboardPage(testContext.getElementUtils());

        this.tc = new TestProofsCollection(testContext);
    }

    @Given("the user is on the login page")
    public void navigateToLoginPage() {
        loginPage.navigateToApplication();
        logger.info("User navigated to application.");
    }

    @When("the user logs into the application with user credentials")
    public void logsInWithValidCredentials() {
        try {
            Map<String, String> testData =
                    JsonDataManager.getLoginCredentials();

            String user = testData.get("Username");
            String pass = testData.get("Password");

            loginPage.enterCredentials(user, pass);
            loginPage.clickLogin();

            logger.info("User logged in successfully with username: {}", user);

            tc.attachStepArtifacts(stepStatus.PASSED);

        } catch (Throwable t) {
            tc.attachStepArtifacts(stepStatus.FAILED);
            throw t;
        }
    }

    @Then("the user should see the {string} overview")
    public void verifyDashboardRedirection(String expectedHeader) {
        Assert.assertTrue(
                dashboardPage.isDashboardVisible(),
                "Dashboard failed to load!"
        );

        logger.info("Dashboard page is visible.");

        String actualHeader = dashboardPage.getHeaderText();

        Assert.assertEquals(
                actualHeader,
                expectedHeader,
                "Header title mismatch on Dashboard!"
        );

        logger.info("Dashboard title is as expected.");
    }

    @Then("the user should see the {string} overview1")
    public void verifyDashboardRedirection1(String expectedHeader) {
        try {
            Assert.assertTrue(
                    dashboardPage.isDashboardVisible(),
                    "Dashboard failed to load!"
            );

            logger.info("Dashboard page is visible.");

            String actualHeader = dashboardPage.getHeaderText();

            Assert.assertEquals(
                    actualHeader,
                    expectedHeader,
                    "Header title mismatch on Dashboard!"
            );

            logger.info("Dashboard title is as expected.");

            tc.attachStepArtifacts(stepStatus.PASSED);

        } catch (Throwable t) {
            tc.attachStepArtifacts(stepStatus.FAILED);
            throw t;
        }
    }

    @When("the user logs into the application with username {string} and password {string}")
    public void performLoginWithDirectData(String user, String pass) {
        // Use the credentials provided directly in the feature file
        loginPage.enterCredentials(user, pass);
        loginPage.clickLogin();

        // Never log passwords
        logger.info("User submitted login credentials.");
    }

    @Then("the user should see the {string} error message")
    public void verifyErrorMessage(String expectedError) {
        String actualError = loginPage.getErrorMessage();

        Assert.assertEquals(actualError, expectedError);

        logger.info("Expected error message is displayed and verified.");
    }

    @Then("the user should see the required field validation")
    public void verifyRequiredFieldValidation() {
        Assert.assertTrue(
                loginPage.isRequiredFieldValidationDisplayed(),
                "Required field validation was not displayed!"
        );
    }

    @When("the user logs out")
    public void userLogsOut() {
        dashboardPage.clickProfileMenu();
        dashboardPage.clickLogout();
    }

    @Then("the user should be redirected to the login page")
    public void verifyLoginPageAfterLogout() {
        Assert.assertTrue(
                loginPage.isLoginPageVisible(),
                "User was not redirected to the login page!"
        );
    }
}
