package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.service.ExtentService;

import pages.LoginPage;
import pages.OtpPage;
import utils.ConfigReader;
import utils.ElementUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Hooks {

    @Before
    public void setUpAndLogin(Scenario scenario) throws InterruptedException {
        System.out.println("-------------------------------------------------");
        System.out.println("⏳ Starting Scenario: " + scenario.getName());

        BaseTest.initializeDriver(ConfigReader.getProperty("browser"));
        WebDriver driver = BaseTest.getDriver();

        driver.get(ConfigReader.getProperty("app.url"));
        Thread.sleep(1000);

        LoginPage loginPage = new LoginPage(driver);
        OtpPage otpPage = new OtpPage(driver);

        loginPage.enterEmail(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='60%'");

        loginPage.clickLoginButton();
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

@AfterAll
public static void afterAllTests() {
    System.out.println("📦 Test execution complete. Flushing ExtentReports and attempting to send PDF report...");

    // 🔄 Flush reports so the PDF gets generated
    ExtentService.getInstance().flush();

    File sparkRoot = new File("test-output");
    File[] sparkDirs = sparkRoot.listFiles((dir, name) -> name.startsWith("SparkReport"));

    if (sparkDirs == null || sparkDirs.length == 0) {
        System.out.println("⚠️ No SparkReport folder found.");
        return;
    }

    Arrays.sort(sparkDirs, Comparator.comparingLong(File::lastModified).reversed());
    File latestSparkDir = sparkDirs[0];

    // 🔍 Look inside the latest SparkReport for the generated PDF
    File pdfFile = new File(latestSparkDir, "test-output/PDFReport/PdfReport.pdf");

    int attempts = 0;
    while (!pdfFile.exists() && attempts < 20) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}
        attempts++;
    }

    if (!pdfFile.exists()) {
        System.out.println("⚠️ PdfReport.pdf still not found after waiting.");
        return;
    }

    System.out.println("📧 Sending PDF report from: " + pdfFile.getAbsolutePath());
    ElementUtils.sendEmailWithAttachment(
        "s.naveenkumar@iamneo.ai",
        "Automation PDF Report",
        "Attached is the test execution report.",
        pdfFile.getAbsolutePath()
    );
}







}
