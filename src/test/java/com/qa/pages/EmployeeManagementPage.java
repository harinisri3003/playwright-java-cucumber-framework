
package com.qa.pages;

import com.qa.utils.ElementUtils;

public class EmployeeManagementPage extends BasePage {

    private String[] pimMenu = {
        "//span[text()='PIM']"
    };

    private String[] employeeListHeader = {
        "//h5[text()='Employee Information']",
        "//h5[text()='Employee List']"
    };

    public EmployeeManagementPage(ElementUtils elementUtils) {
        super(elementUtils);
    }

    public void navigateToEmployeeList() {
        elementUtils.clickElement(pimMenu);
    }

    public boolean isEmployeeListDisplayed() {
        return elementUtils.isElementVisible(employeeListHeader);
    }
}