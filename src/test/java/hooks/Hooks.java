package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.OtpPage;
import utils.ConfigReader;

public class Hooks {

    @Before
    public void setUp() {
        BaseTest.initializeDriver();
    }

    @Before("@event") // Runs only for scenarios tagged with @roles
    public void loginBeforeRolesFeature() throws InterruptedException {
        WebDriver driver = BaseTest.getDriver();
        driver.get(ConfigReader.getProperty("app.url"));

        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        loginPage.enterEmail(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLoginButton();

        otpPage.waitForOtpInputs();
        otpPage.enterOtp("123456"); // Replace with actual OTP handling if needed
        otpPage.clickVerifyAccount();
    }

    @After
    public void tearDown() {
        BaseTest.quitDriver();
    }
}
