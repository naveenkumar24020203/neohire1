package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.EventInfoPage;
import pages.EventPage;

public class EventInfoSteps {

    private final WebDriver driver = BaseTest.getDriver();
    private final EventPage eventPage = new EventPage(driver);
    private final EventInfoPage eventInfoPage = new EventInfoPage(driver);


    @And("I navigate to {string} tab in the event")
    public void iNavigateToTabInTheEvent(String tabName) throws InterruptedException {
        eventInfoPage.clickOnTab(tabName);
        Thread.sleep(3000);
    }


    @And("I click on Add Stage")
    public void iClickOnAddStage() {
        eventInfoPage.clickAddStage();
    }

    
    @Given("I am inside the event page for {string}")
    @And("I search and click on event {string}")
    public void iSearchAndClickOnEvent(String eventName) throws InterruptedException {
        eventPage.navigateToEventsPage(); // Optional if not already on Event page
        eventPage.searchEvent(eventName);
        Thread.sleep(1000); // Add wait if table takes time to load
        eventPage.clickEvent(eventName);
    }

    @And("I create a {string} stage named {string} with dropdown {string} and link {string}")
    public void iCreateStageWithDropdownAndLink(String stageType, String stageName, String dropdownValue, String linkValue) throws InterruptedException {
        eventInfoPage.clickAddStage();
        Thread.sleep(2000);
        eventInfoPage.createStage(stageType, stageName, dropdownValue, linkValue);
        Thread.sleep(200);
    }   

    @And("I create a {string} stage named {string} with dropdown {string}")
    public void iCreateStageWithDropdownOnly(String stageType, String stageName, String dropdownValue) throws InterruptedException {
        eventInfoPage.clickAddStage();
        Thread.sleep(2000);
        eventInfoPage.createStage(stageType, stageName, dropdownValue, null);
        Thread.sleep(200);
    }

    @And("I create a {string} stage named {string}")
    public void iCreateStageWithNameOnly(String stageType, String stageName) throws InterruptedException {
        eventInfoPage.clickAddStage();
        Thread.sleep(2000);
        eventInfoPage.createStage(stageType, stageName, null, null);
        Thread.sleep(200);
    }   

    @Then("I should see a success message for stage creation")
    public void iShouldSeeSuccessMessageForStageCreation() {
        Assert.assertTrue("Stage creation success message not visible", eventInfoPage.isStageCreatedSuccessfully());
    }

    @And("I select {string} items per page in the Stages candidate list")
    public void i_select_items_per_table(String count) throws InterruptedException {
        eventPage.selectItemsPerPage(count);
        Thread.sleep(500);
    }

    @When("I search and select candidate with email {string}")
    public void iSearchAndSelectCandidateWithEmail(String email) throws InterruptedException {
        eventInfoPage.searchCandidateByEmail(email);
        Thread.sleep(1000);
        eventInfoPage.selectCandidateByEmail(email);
    }

    @Then("the candidate should be selected")
    public void theCandidateShouldBeSelected() {
        // Optional: assertion logic or just log
        System.out.println("✅ Candidate is selected.");
    }


    @When("I select all candidates in the table")
    public void iSelectAllCandidatesInTheTable() {
        EventInfoPage eventInfoPage = new EventInfoPage(driver);
        eventInfoPage.selectAllCandidates();
    }

    @And("I click on {string} stage")
    public void i_click_on_stage(String string) throws InterruptedException {
        eventInfoPage.clickStageByName(string);
        Thread.sleep(400);
    }

    @And("In {string} stage I click on {string} option")
    public void in_stage_i_click_on_option(String stageName, String optionName) throws InterruptedException {
        eventInfoPage.clickStageByName(stageName);

        eventInfoPage.clickStageMenuOption(stageName,optionName);
        Thread.sleep(2000);
    }

    @And("I change the name to {string}")
    public void i_change_the_name_to(String name) throws InterruptedException {
        eventInfoPage.renameStage(name);
    }











@When("I perform stage action {string} with the following parameters:")
public void i_perform_stage_action_with_parameters(String action, io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
    Map<String, String> params = dataTable.asMap(String.class, String.class);
    eventInfoPage.performStageAction(action, params);
    Thread.sleep(4000);
}

@When("I perform stage action {string} without parameters")
public void i_perform_stage_action_without_parameters(String action) throws InterruptedException {
    eventInfoPage.performStageAction(action, new HashMap<>());  // Empty params map
        Thread.sleep(4000);

}






}
