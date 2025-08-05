package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},
    plugin = {
        "pretty",                              
        "summary",     
        "json:target/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "html:target/cucumber-report.html",    
    },
    monochrome = true,
    publish = true,
    tags = "@tabnav" //@event, tabnav, selectCandidates, eventDelete, eventCreate, stageAction, candidate, eventCreate, smoke
)

public class TestRunnerTest {
     
}
