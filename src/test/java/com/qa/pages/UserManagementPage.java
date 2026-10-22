package com.qa.pages;

import com.qa.utils.ElementUtils;

public class UserManagementPage extends BasePage {

    private String[] adminMenu = {
        "//span[text()='Admin']"
    };

    private String[] systemUsersHeader = {
        "//h5[text()='System Users']"
    };

    public UserManagementPage(ElementUtils elementUtils) {
        super(elementUtils);
    }

    public void navigateToSystemUsers() {
        elementUtils.clickElement(adminMenu);
    }

    public boolean isSystemUsersPageDisplayed() {
        return elementUtils.isElementVisible(systemUsersHeader);
    }
}