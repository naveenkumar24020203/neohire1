package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CandidatePage;

public class CandidateSteps extends BaseTest {

    CandidatePage candidatePage = new CandidatePage(driver);

    @Given("I am on the Canditate page")
    public void i_am_on_the_canditate_page() {
        candidatePage.navigateToCandidatePage();
    }

    @Given("I click on Invite Candidate button")
    public void i_click_on_button() {
        candidatePage.clickinviteCandidateButton();
    }

    @Given("I select {string} as option")
    public void i_select_as_option(String uploadOption) {
        if (uploadOption.equalsIgnoreCase("Single-Invite")) {
        candidatePage.clickSingleInvite();
    } else if (uploadOption.equalsIgnoreCase("Bulk-Invites")) {
        candidatePage.clickBulkInvites();
    } else {
        throw new IllegalArgumentException("Invalid option: " + uploadOption);
    }
    }

    @Given("I enter First name as {string}")
    public void i_enter_first_name_as(String firstName) throws InterruptedException {
        Thread.sleep(1000);
candidatePage.enterFirstName(firstName);
    }

    @Given("I enter Last name as {string}")
    public void i_enter_last_name_as(String lastName) {
        candidatePage.enterLastName(lastName);
    }

    @Given("I enter Email Id as {string}")
    public void i_enter_email_id_as(String email) {
        candidatePage.enterEmail(email);
    }

    @Given("I select Gender as {string}")
    public void i_select_gender_as(String gender) throws InterruptedException {
        candidatePage.selectGender(gender);
    }

    @Given("I enter Date of birth as {string}")
    public void i_enter_date_of_birth_as(String dob) throws InterruptedException {
        candidatePage.selectDateOfBirth(dob);
    }

    @Given("I select Event category as {string}")
    public void i_select_event_category_as(String eventCategory) throws InterruptedException {
        candidatePage.selectEventCategory(eventCategory);

    }

    @Given("I select Event name as {string}")
    public void i_select_event_name_as(String eventName) throws InterruptedException {
        candidatePage.selectEventName(eventName);
    }

    @Given("I select Source type as {string}")
    public void i_select_source_type_as(String sourceType) throws InterruptedException {
        candidatePage.selectSourceType(sourceType);
    }

    @Given("I select Source name as {string}")
    public void i_select_source_name_as(String sourceName) throws InterruptedException {
        candidatePage.selectSourceName(sourceName);
    }


    @Then("I click Send Invite button")
    public void i_click_send_invite_button() throws InterruptedException {
        candidatePage.clickSendInvite();
        Thread.sleep(2000);
    }

    @Given("I enter mobile number as {string}")
public void i_enter_mobile_number_as(String mobileNumber) {
    candidatePage.enterMobileNumber(mobileNumber);
}
    }
