package com.qa.testutilities;

import com.microsoft.playwright.Page;
import com.qa.contexts.TestContext;
import com.qa.utils.ConfigLoader;

import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProofsCollection {

    private final TestContext testContext;

    private static final Logger logger =
            LoggerFactory.getLogger(TestProofsCollection.class);

    public TestProofsCollection(TestContext testContext) {
        this.testContext = testContext;
    }

    private void attachScreenshot() {
        Page page = testContext.getPage();

        if (page != null && !page.isClosed()) {
            byte[] screenshot = page.screenshot();
            Scenario scenario = testContext.getScenario();

            if (scenario != null) {
                scenario.attach(
                        screenshot,
                        "image/png",
                        "Step Screenshot");
            }
        }
    }

    public void attachStepArtifacts(stepStatus status) {
        try {
            if (status == stepStatus.FAILED) {
                boolean captureFailed = Boolean.parseBoolean(
                        ConfigLoader.getInstance()
                                .getOptionalProp(
                                        "screenshot.for.step.failed"));

                if (captureFailed) {
                    attachScreenshot();
                    logger.debug(
                            "Screenshot attached to failed step");
                }
            } else {
                boolean capturePassed = Boolean.parseBoolean(
                        ConfigLoader.getInstance()
                                .getOptionalProp(
                                        "screenshot.for.step.passed"));

                if (capturePassed) {
                    attachScreenshot();
                    logger.debug(
                            "Screenshot attached to passed step");
                }
            }
        } catch (Exception e) {
            logger.debug(
                    "Failed to attach step screenshot: {}",
                    e.getMessage());
        }
    }
}