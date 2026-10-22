
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
            throw new IllegalArgumentException("Page must not be null.");
        }
        this.page = page;
    }

    private double getTimeout() {
        return Double.parseDouble(
                ConfigLoader.getInstance()
                        .getOptionalProp("timeout.global.wait")
        );
    }

    private void validateSelectors(String action, String... selectors) {
        if (selectors == null || selectors.length == 0) {
            throw new IllegalArgumentException(
                    "At least one selector is required for " + action + "."
            );
        }
    }

    // CLICK

    public void clickElement(String... selectors) {
        validateSelectors("clickElement", selectors);

        RuntimeException lastException = null;

        for (String selector : selectors) {
            try {
                page.locator(selector).click();
                logger.debug("Clicked element using selector: {}", selector);
                return;
            } catch (RuntimeException e) {
                lastException = e;
                logger.debug("Click failed for selector: {}", selector);
            }
        }

        throw new RuntimeException(
                "Unable to click element using selectors: "
                        + String.join(", ", selectors),
                lastException
        );
    }

    // FILL TEXT

    public void enterText(String value, String... selectors) {
        validateSelectors("enterText", selectors);

        RuntimeException lastException = null;

        for (String selector : selectors) {
            try {
                page.locator(selector).fill(value);
                logger.debug("Filled text field using selector: {}", selector);
                return;
            } catch (RuntimeException e) {
                lastException = e;
                logger.debug("Text entry failed for selector: {}", selector);
            }
        }

        throw new RuntimeException(
                "Unable to enter text using selectors: "
                        + String.join(", ", selectors),
                lastException
        );
    }

    // GET TEXT

    public String getElementText(String... selectors) {
        validateSelectors("getElementText", selectors);

        RuntimeException lastException = null;

        for (String selector : selectors) {
            try {
                Locator locator = page.locator(selector);
                locator.waitFor(
                        new Locator.WaitForOptions()
                                .setState(WaitForSelectorState.VISIBLE)
                                .setTimeout(getTimeout())
                );

                return locator.innerText().trim();
            } catch (RuntimeException e) {
                lastException = e;
                logger.debug("Text retrieval failed for selector: {}", selector);
            }
        }

        throw new RuntimeException(
                "Unable to retrieve text using selectors: "
                        + String.join(", ", selectors),
                lastException
        );
    }

    // VISIBILITY

    public boolean isElementVisible(String... selectors) {
        validateSelectors("isElementVisible", selectors);

        for (String selector : selectors) {
            try {
                page.locator(selector).waitFor(
                        new Locator.WaitForOptions()
                                .setState(WaitForSelectorState.VISIBLE)
                                .setTimeout(getTimeout())
                );
                return true;
            } catch (TimeoutError e) {
                logger.debug("Element did not become visible: {}", selector);
            }
        }

        return false;
    }

    // URL WAIT

    public void waitForPageURL(String urlPart) {
        if (urlPart == null || urlPart.isBlank()) {
            throw new IllegalArgumentException(
                    "Expected URL text must not be empty."
            );
        }

        page.waitForURL(
                url -> url.toLowerCase().contains(urlPart.toLowerCase()),
                new Page.WaitForURLOptions().setTimeout(getTimeout())
        );
    }

    // PAGE LOAD

    /**
     * Wait for DOMContentLoaded when a test explicitly requires it.
     * Do not call this after every click; many interactions do not navigate.
     */
    public void waitForPageLoad() {
        page.waitForLoadState(
                LoadState.DOMCONTENTLOADED,
                new Page.WaitForLoadStateOptions().setTimeout(getTimeout())
        );
    }

    /**
     * Wait for the document body to be visible.
     * For dynamic content, wait for the specific expected element or state.
     */
    public void waitForPageStable() {
        page.locator("body").waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(getTimeout())
        );
    }

    // PAGE INFO

    public String getPageTitle() {
        return page.title();
    }

    public Page getPage() {
        return page;
    }
}
