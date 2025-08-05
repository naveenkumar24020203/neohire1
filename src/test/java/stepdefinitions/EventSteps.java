package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;


import org.junit.Assert;
import pages.EventPage;
import utils.DateTimePickerUtils;

public class EventSteps {

    EventPage eventPage = new EventPage(BaseTest.getDriver());

    @Given("I am on the Event page")
    public void i_am_on_create_event_page() throws InterruptedException {
        eventPage.navigateToEventsPage();
        Thread.sleep(300);
    }

    @When("I click on the {string} button on Event page")
    public void i_click_on_the_create_event_button(String buttonName) throws InterruptedException {
        eventPage.clickCreateEventButton();
        Thread.sleep(300);
    }

    @And("I enter event title as {string}")
    public void i_enter_event_title(String title) throws InterruptedException {
        eventPage.enterEventTitle(title);
        Thread.sleep(100);
    }

    @And("I select job role as {string}")
    public void i_select_job_role(String roles) throws InterruptedException {
    String[] roleArray = roles.split(",");
    for (String role : roleArray) {
        eventPage.selectJobRole(role.trim());
    }
}

    @And("I select institution Name as {string}")
    public void i_select_institution_name(String name) throws InterruptedException {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("⚠️ Skipping institution name selection (no input provided).");
            return;
        }
        eventPage.selectInstitutionName(name);
    }

    @And("I enter institution address as {string}")
    public void i_enter_institution_address(String address) throws InterruptedException {
        if (address == null || address.trim().isEmpty()) {
            System.out.println("⚠️ Skipping institution address entry (no input provided).");
            return;
        }
        eventPage.enterInstitutionAddress(address);
    }


    @And("I select event start date as {string}")
    public void i_select_event_start_date_as(String dateTime) throws InterruptedException {
        DateTimePickerUtils.parseAndSelectDate(eventPage::selectEventStartDate, dateTime);
    }

    @And("I select event end date as {string}")
    public void i_select_event_end_date(String dateTime) throws InterruptedException {
        DateTimePickerUtils.parseAndSelectDate(eventPage::selectEventEndDate, dateTime);
    }

    @And("I select registration start date as {string}")
    public void i_select_registration_start_date(String startDate) throws InterruptedException {
        DateTimePickerUtils.parseAndSelectDate(eventPage::selectRegistrationStartDate, startDate);
    }

    @And("I select registration end date as {string}")
    public void i_select_registration_end_date(String endDate) throws InterruptedException {
        DateTimePickerUtils.parseAndSelectDate(eventPage::selectRegistrationEndDate, endDate);
    }
    
    @And("I enter work experience as {string}")
    public void i_enter_work_experience(String experience) {
        eventPage.enterWorkExperience(experience);
    }

    @And("I enter minimum salary as {string}")
    public void i_enter_minimum_salary(String minSalary) throws InterruptedException {
        eventPage.enterMinSalary(minSalary);
    }

    @And("I enter maximum salary as {string}")
    public void i_enter_maximum_salary(String maxSalary) throws InterruptedException {
        eventPage.enterMaxSalary(maxSalary);
    }

    @And("I select registration form as {string}")
    public void i_select_registration_form(String form) throws InterruptedException {
        eventPage.selectRegistrationForm(form);
    }

    @And("I select email template as {string}")
    public void i_select_email_template(String template) throws InterruptedException {
        eventPage.selectEmailTemplate(template);
    }

    @And("I select event category as {string}")
    public void i_select_event_category(String category) throws InterruptedException {
        eventPage.selectEventCategory(category);
    }

    @And("I select event type as {string}")
    public void i_select_event_type(String type) throws InterruptedException {
        eventPage.selectEventType(type);
    }

    @And("I submit the event")
    public void i_submit_event() throws InterruptedException {
        eventPage.clickSave();
        Thread.sleep(200);
    }

    @Then("I should see event created successfully message")
    public void verify_event_creation() {
        Assert.assertTrue(eventPage.isEventCreatedSuccessfully());
    }

    @Then("I search and verify event {string} is visible")
    public void i_search_and_verify_event_is_visible(String eventName) throws InterruptedException {
        Thread.sleep(400);

        eventPage.searchEvent(eventName);
        Thread.sleep(500);
        Assert.assertTrue("Event not found in list!", eventPage.isEventDisplayedInTable(eventName));
    }

    @And("I select {string} items per page in the event table")
    public void i_select_items_per_page(String count) throws InterruptedException {
        eventPage.selectItemsPerPage(count);
    }

    @Then("I click on event {string}")
    public void searchAndClickEvent(String eventName) throws InterruptedException {
    // Assume you already typed in search box
    eventPage.searchEvent(eventName);
    eventPage.clickEvent(eventName);
}

@Then("I search and verify event {string} is not visible")
public void i_search_and_verify_event_is_not_visible(String eventName) throws InterruptedException {
    Thread.sleep(400);
    eventPage.searchEvent(eventName);
    Thread.sleep(400);
    
    boolean isVisible = eventPage.isEventDisplayedInTable(eventName);
    Assert.assertFalse("❌ Event '" + eventName + "' was found in the list, but it should not be.", isVisible);
}


    @Then("I delete the event named {string}")
public void i_delete_the_event_named(String eventName) throws InterruptedException {
    // Write code here that turns the phrase above into concrete actions
    Thread.sleep(500);
    eventPage.deleteEvent(eventName);
}


@Then("I should see event should not be created")
public void i_should_see_event_should_not_be_created() {
    Assert.assertTrue("Expected mandatory field error message is not visible!", eventPage.isMandatoryFieldErrorVisible());

}

}
