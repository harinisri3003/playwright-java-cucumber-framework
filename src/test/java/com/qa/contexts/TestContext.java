package com.qa.contexts;

import com.microsoft.playwright.Page;
import com.qa.factory.DriverFactory;
import com.qa.utils.ElementUtils;

import io.cucumber.java.Scenario;

/**
 * Shared state container for a single Cucumber scenario.
 */
public class TestContext {

    private DriverFactory driverFactory;
    private Page page;
    private ElementUtils elementUtils;
    private String scenarioName;
    private Scenario scenario;

    public TestContext() {
        this.driverFactory = new DriverFactory();
    }

    public DriverFactory getDriverFactory() {
        return driverFactory;
    }

    public Page getPage() {
        if (this.page == null) {
            this.page = driverFactory.initPlaywright();
            this.elementUtils = new ElementUtils(this.page);
        }
        return this.page;
    }

    public ElementUtils getElementUtils() {
        if (this.elementUtils == null) {
            getPage();
        }
        return elementUtils;
    }

    public void setScenarioName(String name) {
        this.scenarioName = name;
    }

    public String getScenarioName() {
        return this.scenarioName;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }
}