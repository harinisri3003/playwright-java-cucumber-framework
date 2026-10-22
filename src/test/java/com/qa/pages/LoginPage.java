package com.qa.pages;

import com.qa.utils.ConfigLoader;
import com.qa.utils.ElementUtils;

public class LoginPage extends BasePage {

    private final String[] usernameField = {
        "input[name='username']",
        "input[placeholder='Username']",
        ".orangehrm-login-form input.oxd-input:first-of-type"
    };

    private final String[] passwordField = {
        "input[name='password']",
        "input[type='password']",
        ".orangehrm-login-form input[type='password']"
    };

    private final String[] loginButton = {
        "button[type='submit']",
        ".orangehrm-login-button",
        "button:has-text('Login')"
    };

    private final String errorMessage =
            ".orangehrm-login-error p";

    public LoginPage(ElementUtils elementUtils) {
        super(elementUtils);
    }

    public void navigateToApplication() {
        String url = ConfigLoader.getInstance()
                .getMandatoryProp("url");

        navigateTo(url);
        elementUtils.waitForPageStable();
    }

    public void enterCredentials(String user, String pass) {
        if (user != null && !user.isEmpty()) {
            elementUtils.enterText(user, usernameField);
        }

        if (pass != null && !pass.isEmpty()) {
            elementUtils.enterText(pass, passwordField);
        }
    }

    public void clickLogin() {
        elementUtils.clickElement(loginButton);
    }

    public String getErrorMessage() {
        return elementUtils.getElementText(errorMessage);
    }

    public boolean isRequiredFieldValidationDisplayed() {
        return elementUtils.isElementVisible(
                ".oxd-input-field-error-message"
        );
    }

    public boolean isLoginPageVisible() {
        return elementUtils.isElementVisible(usernameField);
    }
}
