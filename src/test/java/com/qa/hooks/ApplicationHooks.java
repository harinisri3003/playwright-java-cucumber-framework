package com.qa.hooks;

import com.microsoft.playwright.BrowserContext;
import com.qa.contexts.TestContext;
import com.qa.testutilities.TestProofsCollection;
import com.qa.utils.ConfigLoader;
import com.qa.utils.FailedLocatorCollector;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationHooks {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationHooks.class);

	private final TestContext testContext;

	public ApplicationHooks(TestContext testContext, TestProofsCollection stepHelper) {
		this.testContext = testContext;
	}

	@Before(order = 0)
	public void setupScenario(Scenario scenario) {
		testContext.setScenario(scenario);
		testContext.setScenarioName(scenario.getName());
		FailedLocatorCollector.setScenarioName(scenario.getName());

		logger.info("Starting scenario: {}", scenario.getName());
	}

	@Before(order = 1)
	public void startTrace() {
		testContext.getPage();

		BrowserContext context = testContext.getDriverFactory().getTlBrowserContext().get();

		if (context == null) {
			logger.warn("Browser context is unavailable; skipping trace.");
			return;
		}

		context.tracing().start(new com.microsoft.playwright.Tracing.StartOptions().setScreenshots(true)
				.setSnapshots(true).setSources(true));
	}

	@After(order = 3)
	public void captureScenarioScreenshot(Scenario scenario) {
		String outcome = scenario.getStatus().name().toLowerCase();

		boolean captureFailure = Boolean
				.parseBoolean(ConfigLoader.getInstance().getOptionalProp("screenshot.on.scenario.failure"));

		boolean captureSuccess = Boolean
				.parseBoolean(ConfigLoader.getInstance().getOptionalProp("screenshot.on.scenario.success"));

		boolean captureSkipped = Boolean
				.parseBoolean(ConfigLoader.getInstance().getOptionalProp("screenshot.on.scenario.skipped"));

		boolean shouldCapture = (scenario.isFailed() && captureFailure)
				|| (scenario.getStatus() == Status.PASSED && captureSuccess)
				|| (scenario.getStatus() == Status.SKIPPED && captureSkipped);

		if (shouldCapture) {
			try {
				com.microsoft.playwright.Page page = testContext.getPage();

				if (page != null && !page.isClosed()) {
					scenario.attach(page.screenshot(), "image/png", "Scenario Screenshot - " + outcome);
				}
			} catch (Exception e) {
				logger.warn("Could not capture scenario screenshot: {}", e.getMessage());
			}
		}
	}

	@After(order = 2)
	public void captureTrace(Scenario scenario) {
		BrowserContext context = testContext.getDriverFactory().getTlBrowserContext().get();

		if (context == null) {
			return;
		}

		try {
			if (scenario.isFailed()) {
				Path traceDirectory = Paths.get("test-output", "traces");
				Files.createDirectories(traceDirectory);

				String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9._-]", "_");

				Path tracePath = traceDirectory.resolve(safeName + ".zip");

				context.tracing().stop(new com.microsoft.playwright.Tracing.StopOptions().setPath(tracePath));

				logger.info("Playwright trace saved: {}", tracePath);
			} else {
				context.tracing().stop();
			}
		} catch (Exception e) {
			logger.warn("Could not save Playwright trace: {}", e.getMessage());
		}
	}

	@After(order = 0)
	public void quitBrowser(Scenario scenario) {
		logger.info("Scenario '{}' finished with status: {}", scenario.getName(), scenario.getStatus());

		testContext.getDriverFactory().closePlaywright();
	}
}