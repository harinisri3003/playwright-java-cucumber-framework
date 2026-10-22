package com.qa.stepdefinitions;

import com.qa.contexts.TestContext;
import com.qa.pages.UserManagementPage;
import com.qa.utils.ElementUtils;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class UserManagementSteps {

    private final UserManagementPage userManagementPage;

    public UserManagementSteps(TestContext testContext) {
        ElementUtils elementUtils = testContext.getElementUtils();
        userManagementPage = new UserManagementPage(elementUtils);
    }

    @When("the user navigates to the System Users page")
    public void navigateToSystemUsers() {
        userManagementPage.navigateToSystemUsers();
    }

    @Then("the System Users page should be displayed")
    public void verifySystemUsersPageDisplayed() {
        Assert.assertTrue(
            userManagementPage.isSystemUsersPageDisplayed(),
            "System Users page was not displayed!"
        );
    }
}