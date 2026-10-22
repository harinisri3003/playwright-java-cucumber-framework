
package com.qa.stepdefinitions;

import com.qa.contexts.TestContext;
import com.qa.pages.EmployeeManagementPage;
import com.qa.utils.ElementUtils;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class EmployeeManagementSteps {

    private final EmployeeManagementPage employeeManagementPage;

    public EmployeeManagementSteps(TestContext testContext) {
        ElementUtils elementUtils = testContext.getElementUtils();
        employeeManagementPage = new EmployeeManagementPage(elementUtils);
    }

    @When("the user navigates to the Employee List page")
    public void navigateToEmployeeList() {
        employeeManagementPage.navigateToEmployeeList();
    }

    @Then("the employee list page should be displayed")
    public void verifyEmployeeListDisplayed() {
        Assert.assertTrue(
            employeeManagementPage.isEmployeeListDisplayed(),
            "Employee List page was not displayed!"
        );
    }
}