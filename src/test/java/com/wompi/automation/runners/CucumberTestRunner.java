package com.wompi.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Cucumber Test Runner for Wompi API tests
 * Configures Cucumber to run with TestNG
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.wompi.automation.steps",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/html-report.html",
                "json:target/cucumber-reports/json-report/Cucumber.json",
                "junit:target/cucumber-reports/xml-report/Cucumber.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "@positive or @negative",
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
