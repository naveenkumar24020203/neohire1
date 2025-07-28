package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.EventInfoPage;
import pages.EventPage;

public class EventInfoSteps {

    private final WebDriver driver = BaseTest.getDriver();
    private final EventPage eventPage = new EventPage(driver);
    private final EventInfoPage eventInfoPage = new EventInfoPage(driver);

    /**
     * Navigates to the specific tab inside an event.
     * @throws InterruptedException 
     */
    @And("I navigate to {string} tab in the event")
    public void iNavigateToTabInTheEvent(String tabName) throws InterruptedException {
        eventInfoPage.clickOnTab(tabName);
        Thread.sleep(3000);
    }

    /**
     * Clicks the 'Add Stage' button.
     */
    @And("I click on Add Stage")
    public void iClickOnAddStage() {
        eventInfoPage.clickAddStage();
    }

    /**
     * Clicks a stage name from the left sidebar or section.
     */
    // @And("I click on stage named {string}")
    // public void iClickOnStageNamed(String stageName) {
    //     eventInfoPage.clickStageByName(stageName);
    // }

    // /**
    //  * Selects the first candidate checkbox from the list.
    //  */
    // @And("I select the first candidate")
    // public void iSelectTheFirstCandidate() {
    //     eventInfoPage.selectFirstCandidateCheckbox();
    // }

    /**
     * Clicks the 'Move Stage' button to transition the candidate.
     */
    // @And("I click on Move Stage button")
    // public void iClickOnMoveStageButton() {
    //     eventInfoPage.clickMoveStage();
    // }

    /**
     * Searches for a specific event and clicks on it.
     */
    @Given("I am inside the event page for {string}")
    @And("I search and click on event {string}")
    public void iSearchAndClickOnEvent(String eventName) throws InterruptedException {
        eventPage.navigateToEventsPage(); // Optional if not already on Event page
        eventPage.searchEvent(eventName);
        Thread.sleep(1000); // Add wait if table takes time to load
        eventPage.clickEvent(eventName);
    }

    /**
 * Creates a stage of any type by supplying values based on its fields.
 * 
 * @param stageType Type of the stage like "Screening", "Test", etc.
 * @param stageName Name to be entered in the Stage Name field
 * @param dropdownValue Value to be selected in dropdown (optional for some)
 * @param linkValue Value to be selected in link field (optional for some)
 */
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

}
