package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.OtpPage;
import utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSteps {

    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);

    private WebDriver driver;
    private LoginPage loginPage;
    private OtpPage otpPage;

    @Given("I launch the application")
    public void i_launch_the_application() {
        logger.info("Launching the application...");
        driver = BaseTest.getDriver();
        driver.get(ConfigReader.getProperty("app.url"));
        logger.info("Navigated to URL: {}", ConfigReader.getProperty("app.url"));

        loginPage = new LoginPage(driver);
        otpPage = new OtpPage(driver);
    }

    @When("I enter username and password")
    public void i_enter_username_and_password() {
        String username = ConfigReader.getProperty("username");
        logger.info("Entering username: {}", username);
        loginPage.enterEmail(username);
        loginPage.enterPassword(ConfigReader.getProperty("password"));
    }

    @And("I click the login button")
    public void i_click_the_login_button() throws InterruptedException {
        logger.info("Clicking the login button...");
        loginPage.clickLoginButton();
    }

    @When("I enter OTP {string}")
    public void i_enter_otp(String otp) {
        logger.info("Entering OTP: {}", otp);
        otpPage.waitForOtpInputs();
        otpPage.enterOtp(otp);
    }

    @And("I click the Verify Account button")
    public void i_click_verify_account() throws InterruptedException {
        logger.info("Clicking the Verify Account button...");
        otpPage.clickVerifyAccount();
        Thread.sleep(3000);
    }

    @Then("I should see an OTP error message")
    public void i_should_see_an_otp_error_message() {
        logger.info("Verifying OTP error message is displayed...");
        Assert.assertTrue("OTP error not shown", otpPage.isOtpErrorVisible());
    }

    @Then("I should see Events page")
    public void i_should_see_events_page(){
        Assert.assertTrue("Not landed on event page", loginPage.isManageEventTextDisplayed());
        logger.info("Landed on Events page after Successfull login");
    }
}











