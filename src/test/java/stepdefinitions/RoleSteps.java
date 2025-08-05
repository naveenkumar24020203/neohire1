package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import pages.EventPage;
import pages.RolePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleSteps {

    private static final Logger logger = LoggerFactory.getLogger(RoleSteps.class);

    WebDriver driver;
    RolePage rolePage;
    EventPage eventPage = new EventPage(BaseTest.getDriver());

    @Given("I am on the Roles page")
    public void i_am_on_roles_page() {
        driver = BaseTest.getDriver();
        rolePage = new RolePage(driver);

        rolePage.navigateToRolesPage();
        logger.info("Navigated to Roles page.");
    }

    @When("I click on the {string} button")
    public void i_click_on_the_button(String buttonName) {
        rolePage.clickCreateRoleButton();
        logger.info("Clicked on button: {}", buttonName);
    }

    @And("I enter role name {string}")
    public void i_enter_role_name(String roleName) throws InterruptedException {
        rolePage.enterRoleName(roleName);
        logger.info("Entered role name: {}", roleName);
    }

    @And("I enter role type {string}")
    public void i_enter_role_type(String roleType) throws InterruptedException {
        rolePage.selectRoleType(roleType);
        logger.info("Entered role type: {}", roleType);
    }

    @And("I enter work mode as {string}")
    public void i_enter_work_mode(String workMode) throws InterruptedException {
        rolePage.selectWorkMode(workMode);
        logger.info("Entered work mode: {}", workMode);
    }


    @And("I enter country as {string}")
    public void i_enter_country(String country) throws InterruptedException {
        rolePage.selectCountry(country);
        logger.info("Entered country: {}", country);
    }

    @And("I enter state as {string}")
    public void i_enter_state(String state) throws InterruptedException {
        rolePage.selectState(state);
        logger.info("Entered state: {}", state);
    }

    @And("I enter city as {string}")
    public void i_enter_city(String city) throws InterruptedException {
        rolePage.selectCity(city);
        logger.info("Entered city: {}", city);
    }

    @And("I enter business unit as {string}")
    public void i_enter_business_unit(String businessUnit) throws InterruptedException {
        rolePage.enterBusinessUnit(businessUnit);
        logger.info("Entered Business unit: {}", businessUnit);
    }

    @And("I click the {string} button")
    public void i_click_the_save_button(String buttonName) throws InterruptedException {
        rolePage.clickSaveButton();
        logger.info("Clicked on: {}", buttonName);
    }

    @Then("I should see a success message {string}")
    public void i_should_see_success_message(String expectedMessage) throws InterruptedException {
        Assert.assertTrue("Success message not displayed!", rolePage.isSuccessMessageVisible());
        logger.info("Verified success message: {}", expectedMessage);
        driver.navigate().refresh();
        Thread.sleep(3000);
    }

    @Then("I should see role should not be created")
public void i_should_see_role_should_not_be_created() {
    Assert.assertTrue("Expected mandatory field error message is not visible!", eventPage.isMandatoryFieldErrorVisible());

}
}
