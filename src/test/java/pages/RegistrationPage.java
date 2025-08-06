package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // ✅ Proper PageFactory init
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "(//div[@class='email-group']//input)[1]")
    private WebElement emailInput;

    @FindBy(xpath = "(//div[@class='email-group']//input)[2]")
    private WebElement confirmEmailInput;

    @FindBy(xpath = "//div[@class='verifyOtp ng-star-inserted']//p-button")
    private WebElement verifyButton;
    @FindBy(xpath = "//p-button[@label='Verify']//button")
    private WebElement verifySubmitButton;
    @FindBy(css = "div.otp-boxes input.otp-input")
    private static List<WebElement> otpInputs;

    @FindBy(xpath = "//div[@aria-label='dropdown trigger']")
    private WebElement genderDropdownTrigger;

    @FindBy(xpath = "//p[normalize-space(text())='Registration number']/following-sibling::span//input")
    private WebElement registrationNumberInput;

    @FindBy(xpath = "//p[normalize-space(text())='Company name']/following-sibling::span//input")
    private WebElement companyInput;

    @FindBy(xpath = "//button[@label='Submit']")
    private WebElement submitBtn;

    // Instance methods
    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void confirmEmail(String email) {
        confirmEmailInput.sendKeys(email);
    }

    public void clickVerify() {
        verifyButton.click();
    }

// Existing - Keep as it is if calling statically
public static void enterOtp(String otp) {
    if (otp.length() != 6) {
        throw new IllegalArgumentException("OTP must be exactly 6 digits.");
    }

    for (int i = 0; i < 6; i++) {
        WebElement input = otpInputs.get(i);
        input.clear();
        input.sendKeys(String.valueOf(otp.charAt(i)));
    }

    System.out.println("✅ OTP entered: " + otp);
}


    public void selectGender(String gender) throws InterruptedException {
        ElementUtils.clickAndSelectDropdownValue(driver, genderDropdownTrigger, gender);
    }

    public void enterRegNumber(String regNo) {
        registrationNumberInput.clear();
        registrationNumberInput.sendKeys(regNo);
    }

    public void enterCompanyName(String name) {
        companyInput.clear();
        companyInput.sendKeys(name);
    }

    public void clickSubmit() {
        submitBtn.click();
    }

        public void clickVerifySubmit() {
        verifySubmitButton.click();
    }
}

