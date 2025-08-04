package pages;

import base.BaseTest;
import utils.DateTimePickerUtils;
import utils.ElementUtils;
import utils.NavigationUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CandidatePage {
    WebDriver driver;
    WebDriverWait wait;

    public CandidatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//button[@label='Invite Candidate']")
    private WebElement inviteCandidateBtn;

    // Single-Invite radio button
    @FindBy(xpath = "//input[@type='radio' and @value='single-invite']")
    private WebElement singleInviteRadio;

    // Bulk-Invites radio button
    @FindBy(xpath = "//input[@type='radio' and @value='bulk-invite']")
    private WebElement bulkInviteRadio;

    @FindBy(xpath = "//input[@role='searchbox']")
    private WebElement searchInDropDown;

    @FindBy(xpath = "//div[contains(@class,'bulkUpload-dropzone')]//input[@type='file']")
    private WebElement bulkUploadInput;

    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='gender']")
    private WebElement genderDropdown;

    @FindBy(xpath = "//p-calendar[@formcontrolname='date_of_birth']")
    private WebElement dobCalendar;

    @FindBy(xpath = "//input[@formcontrolname='mobileNumber']")
    private WebElement mobileNumberInput;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='eventCategory']")
    private WebElement eventCategoryDropdown;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='eventName']")
    private WebElement eventNameDropdown;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='sourceType']")
    private WebElement sourceTypeDropdown;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='sourceName']")
    private WebElement sourceNameDropdown;

    @FindBy(xpath = "//button[@label='Send Invite']")
    private WebElement sendInviteButton;


    public void navigateToCandidatePage() throws InterruptedException{
                Thread.sleep(1500);
                ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='75%'");
        Thread.sleep(1500);
                NavigationUtils.navigateToSideMenu(BaseTest.getDriver(), "Manage Candidate");

    }
    public void clickinviteCandidateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(inviteCandidateBtn)).click();
    }


    public void clickSingleInvite() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement singleInviteRadioInput = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@type='radio' and @value='single-invite']")));

    // Check if already selected
    boolean isSelected = singleInviteRadioInput.isSelected();

    if (!isSelected) {
        // Click associated label instead of hidden radio input
        WebElement label = driver.findElement(By.xpath("//label[@for='single-invite']"));
        wait.until(ExpectedConditions.elementToBeClickable(label)).click();
    }
}


public void clickBulkInvites() {
    wait.until(ExpectedConditions.elementToBeClickable(bulkInviteRadio)).click();

}

    public void enterFirstName(String firstName) {
    firstNameInput.clear();
    firstNameInput.sendKeys(firstName);
}

public void enterLastName(String lastName) {
    lastNameInput.clear();
    lastNameInput.sendKeys(lastName);
}

public void enterEmail(String email) {
    emailInput.clear();
    emailInput.sendKeys(email);
}

public void selectGender(String genderText) throws InterruptedException {
    ElementUtils.clickAndSelectDropdownValue(driver, genderDropdown, genderText);
}


public void selectDateOfBirth(String dob) throws InterruptedException {
    // Expected format: "dd-MM-yyyy"
    String[] parts = dob.split("-");
    String day = parts[0];    // "24"
    String month = parts[1];  // "02"
    String year = parts[2];   // "2003"

    By dobCalendarTrigger = By.xpath("//button[@aria-label='Choose Date']");
    DateTimePickerUtils.selectDateOnly(driver, dobCalendarTrigger, year, month, day);
}


public void enterMobileNumber(String mobile) {
    mobileNumberInput.clear();
    mobileNumberInput.sendKeys(mobile);
}

public void selectEventCategory(String category) throws InterruptedException {
    ElementUtils.clickAndSelectDropdownValue(driver, eventCategoryDropdown, category);

}


public void selectEventName(String eventName) throws InterruptedException{
        ElementUtils.searchAndSelectFromDropdown(eventNameDropdown,searchInDropDown,eventName,driver);

}

public void selectSourceType(String sourceType) throws InterruptedException  {
    ElementUtils.clickAndSelectDropdownValue(driver, sourceTypeDropdown, sourceType);

}

public void selectSourceName(String sourceName) throws InterruptedException{
    ElementUtils.clickAndSelectDropdownValue(driver, sourceNameDropdown, sourceName);

}

public void clickSendInvite() {
    sendInviteButton.click();
}

}
