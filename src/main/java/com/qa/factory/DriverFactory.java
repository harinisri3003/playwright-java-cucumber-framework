package com.qa.factory;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qa.utils.ConfigLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class DriverFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(DriverFactory.class);

    private final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private final ThreadLocal<BrowserContext> tlBrowserContext =
            new ThreadLocal<>();
    private final ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public ThreadLocal<Playwright> getTlPlaywright() {
        return tlPlaywright;
    }

    public ThreadLocal<Browser> getTlBrowser() {
        return tlBrowser;
    }

    public ThreadLocal<BrowserContext> getTlBrowserContext() {
        return tlBrowserContext;
    }

    public ThreadLocal<Page> getTlPage() {
        return tlPage;
    }

    public Page initPlaywright() {
        ConfigLoader config = ConfigLoader.getInstance();

        String browserName = System.getProperty(
                "browser",
                config.getMandatoryProp("browser")
        );

        String headlessProperty = System.getProperty(
                "headless",
                config.getOptionalProp("headless")
        );

        boolean headless = Boolean.parseBoolean(headlessProperty);

        if (browserName == null || browserName.isBlank()) {
            throw new IllegalStateException(
                    "Browser is not configured."
            );
        }

        try {
            Playwright playwright = Playwright.create();
            tlPlaywright.set(playwright);

            BrowserType browserType;

            switch (browserName.trim().toLowerCase()) {
                case "chromium":
                    browserType = playwright.chromium();
                    break;
                case "firefox":
                    browserType = playwright.firefox();
                    break;
                case "webkit":
                    browserType = playwright.webkit();
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browserName
                    );
            }

            Browser browser = browserType.launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(headless)
                            .setArgs(Arrays.asList(
                                    "--disable-extensions",
                                    "--allow-insecure-localhost"
                            ))
            );
            tlBrowser.set(browser);

            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setViewportSize(null)
            );
            tlBrowserContext.set(context);

            PlaywrightAssertions.setDefaultAssertionTimeout(
                    Long.parseLong(
                            config.getOptionalProp(
                                    "timeout.default.assertion"
                            )
                    )
            );

            context.setDefaultTimeout(
                    Long.parseLong(
                            config.getOptionalProp("timeout.global.wait")
                    )
            );

            context.setDefaultNavigationTimeout(
                    Long.parseLong(
                            config.getOptionalProp("timeout.page.load")
                    )
            );

            Page page = context.newPage();
            tlPage.set(page);

            logger.info("Browser launched successfully: {}", browserName);
            return page;

        } catch (RuntimeException e) {
            closePlaywright();
            throw new IllegalStateException(
                    "Failed to initialize browser: " + browserName,
                    e
            );
        }
    }

    public void closePlaywright() {
        try {
            closeSafely("Page", () -> {
                if (tlPage.get() != null) {
                    tlPage.get().close();
                }
            });

            closeSafely("Browser context", () -> {
                if (tlBrowserContext.get() != null) {
                    tlBrowserContext.get().close();
                }
            });

            closeSafely("Browser", () -> {
                if (tlBrowser.get() != null) {
                    tlBrowser.get().close();
                }
            });

            closeSafely("Playwright", () -> {
                if (tlPlaywright.get() != null) {
                    tlPlaywright.get().close();
                }
            });

        } finally {
            tlPage.remove();
            tlBrowserContext.remove();
            tlBrowser.remove();
            tlPlaywright.remove();
        }
    }

    private void closeSafely(String resource, Runnable closeAction) {
        try {
            closeAction.run();
            logger.debug("{} closed.", resource);
        } catch (Exception e) {
            logger.warn(
                    "Could not close {} cleanly: {}",
                    resource,
                    e.getMessage()
            );
        }
    }
}