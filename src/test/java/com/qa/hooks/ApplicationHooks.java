package com.qa.hooks;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;

import com.qa.contexts.TestContext;
import com.qa.testutilities.TestProofsCollection;
import com.qa.utils.ConfigLoader;
import com.qa.utils.FailedLocatorCollector;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.qameta.allure.Allure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationHooks {

    private static final Logger logger =
            LoggerFactory.getLogger(ApplicationHooks.class);

    private final TestContext testContext;

    public ApplicationHooks(
            TestContext testContext,
            TestProofsCollection stepHelper) {
        this.testContext = testContext;
    }

    @Before(order = 0)
    public void setupScenario(Scenario scenario) {
        testContext.setScenarioName(scenario.getName());
        FailedLocatorCollector.setScenarioName(scenario.getName());

        logger.info("SCENARIO STARTED: {}", scenario.getName());
    }

    @Before(order = 1)
    public void startTrace() {
        Page page = testContext.getPage();

        page.context().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
    }

    @After(order = 3)
    public void captureScenarioScreenshot(Scenario scenario) {
        String property;

        if (scenario.getStatus() == Status.FAILED) {
            property = "screenshot.on.scenario.failure";
        } else if (scenario.getStatus() == Status.PASSED) {
            property = "screenshot.on.scenario.success";
        } else if (scenario.getStatus() == Status.SKIPPED) {
            property = "screenshot.on.scenario.skipped";
        } else {
            return;
        }

        boolean enabled = Boolean.parseBoolean(
                ConfigLoader.getInstance().getOptionalProp(property)
        );

        if (!enabled) {
            return;
        }

        try {
            Page page = testContext.getPage();

            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions()
                            .setFullPage(true)
                            .setTimeout(15000)
            );

            scenario.attach(
                    screenshot,
                    "image/png",
                    "Scenario Screenshot"
            );

        } catch (Exception e) {
            logger.warn(
                    "Could not capture screenshot for '{}': {}",
                    scenario.getName(),
                    e.getMessage()
            );
        }
    }

    @After(order = 2)
    public void captureTrace(Scenario scenario) {
        BrowserContext context = testContext.getDriverFactory()
                .getTlBrowserContext().get();

        if (context == null) {
            return;
        }

        try {
            if (!scenario.isFailed()) {
                context.tracing().stop();
                return;
            }

            Path tracePath = Paths.get(
                    "test-output",
                    "traces",
                    scenario.getName().replaceAll(
                            "[^a-zA-Z0-9._-]", "_"
                    ) + ".zip"
            );

            Files.createDirectories(tracePath.getParent());

            context.tracing().stop(
                    new Tracing.StopOptions().setPath(tracePath)
            );

            try (InputStream input = Files.newInputStream(tracePath)) {
                Allure.addAttachment(
                        "Playwright Trace",
                        "application/zip",
                        input,
                        ".zip"
                );
            }

            logger.info("Playwright trace saved: {}", tracePath);

        } catch (Exception e) {
            logger.error(
                    "Could not capture trace for '{}'",
                    scenario.getName(),
                    e
            );
        }
    }

    @After(order = 0)
    public void quitBrowser(Scenario scenario) {
        logger.info(
                "SCENARIO FINISHED: {} | Status: {}",
                scenario.getName(),
                scenario.getStatus()
        );

        testContext.getDriverFactory().closePlaywright();
    }
}