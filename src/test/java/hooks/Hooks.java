package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.OtpPage;
import utils.ConfigReader;

public class Hooks {

    @Before
    public void setUpAndLogin(Scenario scenario) throws InterruptedException {
        System.out.println("-------------------------------------------------");
        System.out.println("⏳ Starting Scenario: " + scenario.getName());

        // Initialize WebDriver
        BaseTest.initializeDriver();
        WebDriver driver = BaseTest.getDriver();

        // Navigate to the application
        driver.get(ConfigReader.getProperty("app.url"));

        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");


        // Perform login
        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        loginPage.enterEmail(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        Thread.sleep(1000);

        loginPage.clickLoginButton();

        // Handle OTP
        otpPage.waitForOtpInputs();
        otpPage.enterOtp("123456"); // Replace with dynamic OTP logic if needed
        otpPage.clickVerifyAccount();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("❌ FAILED Scenario: " + scenario.getName());
        } else {
            System.out.println("✅ PASSED Scenario: " + scenario.getName());
        }
        System.out.println("-------------------------------------------------\n");

        BaseTest.quitDriver();
    }
}
