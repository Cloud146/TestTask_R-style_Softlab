package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Cucumber/Steps",
        plugin = {
                "pretty",
                "json:cucumber.json"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests{
}
