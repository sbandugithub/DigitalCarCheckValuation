package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature", // Path to feature files
        glue = "stepdefinitions",           // Package of step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Report plugins
        monochrome = true                   // Make console output more readable

)
public class TestRunner {
}