package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OtpPage {
    private WebDriverWait wait;

    // Constructor
    public OtpPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(xpath = "//div[@class='otp-inputs']/input")
    private List<WebElement> otpInputs;

    @FindBy(xpath = "//button[.//span[text()='Verify Account']]")
    private WebElement verifyAccountButton;

    @FindBy(xpath = "//*[text()='OTP expired or invalid.']")
    private WebElement otpErrorMessage;

    // Actions
    public void waitForOtpInputs() {
        wait.until(ExpectedConditions.visibilityOfAllElements(otpInputs));
    }

    public void enterOtp(String otp) {
        for (int i = 0; i < otp.length() && i < otpInputs.size(); i++) {
            otpInputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
    }

    public void clickVerifyAccount() throws InterruptedException {
        Thread.sleep(400);
        wait.until(ExpectedConditions.elementToBeClickable(verifyAccountButton)).click();
    }

    public boolean isOtpErrorVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(otpErrorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
