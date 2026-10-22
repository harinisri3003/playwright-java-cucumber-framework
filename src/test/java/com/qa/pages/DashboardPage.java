package com.qa.pages;

import org.slf4j.Logger;
import com.microsoft.playwright.Page;
import org.slf4j.LoggerFactory;

import com.qa.utils.ElementUtils;
import com.qa.utils.FailedLocatorCollector;

public class DashboardPage extends BasePage {

    // Locators as Arrays for Self-Healing
    private String[] dashboardHeader = {
            "h6.oxd-text--h6",
            "//h6[text()='Dashboard']",
            ".oxd-topbar-header-breadcrumb-module"
    };
    
    private String[] profileMenu = {
    	    ".oxd-userdropdown-tab"
    	};

    	private String[] logoutButton = {
    	    "//a[text()='Logout']"
    	};

    // Constructor receiving the 'Injected' utility
    public DashboardPage(ElementUtils elementUtils) {
        super(elementUtils);
    }

    public boolean isDashboardVisible() {
        elementUtils.waitForPageURL("dashboard");
        return elementUtils.isElementVisible(dashboardHeader);
    }

    public String getHeaderText() {
        return elementUtils.getElementText(dashboardHeader);
    }
    
    public void clickProfileMenu() {
        elementUtils.clickElement(profileMenu);
    }

    public void clickLogout() {
        elementUtils.clickElement(logoutButton);
    }
}
