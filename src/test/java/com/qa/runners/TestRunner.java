package com.qa.runners;

import com.qa.testutilities.AllureEnvironmentManager;
import com.qa.utils.ConfigLoader;
import com.qa.utils.FailedLocatorCollector;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.qa.stepdefinitions", "com.qa.hooks"},
    tags = "not @Ignore",
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "rerun:target/failed_scenarios.txt"
    },
    dryRun = false,
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private static final Logger logger =
            LoggerFactory.getLogger(TestRunner.class);

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        ConfigLoader.getInstance();
        AllureEnvironmentManager.writeEnvironmentInfo();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        logger.info("Test execution completed.");
        FailedLocatorCollector.generateJsonReport();
    }
}