package com.qa.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementUtils {

    private static final Logger logger =
            LoggerFactory.getLogger(ElementUtils.class);

    private final Page page;

    public ElementUtils(Page page) {
        if (page == null) {
            throw new IllegalArgumentException(
                    "Page must not be null."
            );
        }
        this.page = page;
    }

    private double getTimeout() {
        String configuredTimeout = ConfigLoader.getInstance()
                .getOptionalProp("timeout.global.wait");

        if (configuredTimeout == null || configuredTimeout.isBlank()) {
            throw new IllegalStateException(
                    "Missing configuration: timeout.global.wait"
            );
        }

        try {
            double timeout = Double.parseDouble(configuredTimeout);

            if (!Double.isFinite(timeout) || timeout <= 0) {
                throw new IllegalStateException(
                        "timeout.global.wait must be a positive number."
                );
            }

            return timeout;
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Invalid configuration for timeout.global.wait: "
                            + configuredTimeout,
                    e
            );
        }
    }

    private void validateSelectors(String action, String... selectors) {
        if (selectors == null || selectors.length == 0) {
            throw new IllegalArgumentException(
                    "At least one selector is required for " + action + "."
            );
        }

        for (String selector : selectors) {
            if (selector == null || selector.isBlank()) {
                throw new IllegalArgumentException(
                        "Selectors must not be null or blank for "
                                + action + "."
                );
            }
        }
    }

    private Locator getLocator(String... selectors) {
        validateSelectors("getLocator", selectors);

        double timeoutPerSelector = Math.max(
                500,
                getTimeout() / selectors.length
        );

        for (String selector : selectors) {
            Locator locator = page.locator(selector).first();

            try {
                locator.waitFor(
                        new Locator.WaitForOptions()
                                .setState(WaitForSelectorState.VISIBLE)
                                .setTimeout(timeoutPerSelector)
                );

                return locator;

            } catch (TimeoutError e) {
                logger.debug("Selector not visible, trying next fallback: {}", selector
                );
            }
        }

        throw new TimeoutError(
                "No visible element found for selectors: "
                        + String.join(", ", selectors)
        );
    }

    public void clickElement(String... selectors) {
        Locator locator = getLocator(selectors);
        locator.click();

        logger.debug("Clicked element using selector: {}", selectors[0]);
    }

    public void enterText(String value, String... selectors) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "Text value must not be null."
            );
        }

        Locator locator = getLocator(selectors);
        locator.fill(value);

        logger.debug("Filled text field using selector: {}", selectors[0]);
    }

    public String getElementText(String... selectors) {
        Locator locator = getLocator(selectors);

        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(getTimeout())
        );

        return locator.innerText().trim();
    }

    public boolean isElementVisible(String... selectors) {
        validateSelectors("isElementVisible", selectors);

        double timeoutPerSelector = Math.max(
                500,
                getTimeout() / selectors.length
        );

        for (String selector : selectors) {
            try {
                page.locator(selector).first().waitFor(
                        new Locator.WaitForOptions()
                                .setState(WaitForSelectorState.VISIBLE)
                                .setTimeout(timeoutPerSelector)
                );

                return true;

            } catch (TimeoutError e) {
                logger.debug("Element not visible, trying next selector: {}", selector
                );
            }
        }

        return false;
    }

    public void waitForPageURL(String urlPart) {
        if (urlPart == null || urlPart.isBlank()) {
            throw new IllegalArgumentException(
                    "Expected URL text must not be empty."
            );
        }

        page.waitForURL(
                url -> url.toLowerCase().contains(
                        urlPart.toLowerCase()
                ),
                new Page.WaitForURLOptions().setTimeout(getTimeout())
        );
    }

    public void waitForPageLoad() {
        page.waitForLoadState(
                LoadState.DOMCONTENTLOADED,
                new Page.WaitForLoadStateOptions().setTimeout(getTimeout())
        );
    }

    public void waitForPageStable() {
        page.locator("body").waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(getTimeout())
        );
    }

    public String getPageTitle() {
        return page.title();
    }

    public Page getPage() {
        return page;
    }
}