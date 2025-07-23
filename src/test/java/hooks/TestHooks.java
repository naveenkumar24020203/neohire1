package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class TestHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("-------------------------------------------------");
        System.out.println("⏳ Starting Scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("❌ FAILED Scenario: " + scenario.getName());
        } else {
            System.out.println("✅ PASSED Scenario: " + scenario.getName());
        }
        System.out.println("-------------------------------------------------\n");
    }
}
