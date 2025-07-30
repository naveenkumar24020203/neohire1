package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ElementUtils;

import static utils.ElementUtils.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


public class EventInfoPage {

    private WebDriver driver;

    public EventInfoPage(WebDriver driver) {
        this.driver = driver;
            PageFactory.initElements(driver, this);  // ← This line is essential

    }

    // Tab Locators
    @FindBy(xpath = "//button[contains(.,'Candidates') and contains(@class,'tabs')]")
    public WebElement candidatesTab;

    @FindBy(xpath = "//button[contains(.,'Event Details') and contains(@class,'tabs')]")
    public WebElement eventDetailsTab;

    @FindBy(xpath = "//button[contains(.,'Stages') and contains(@class,'tabs')]")
    public WebElement stagesTab;

    @FindBy(xpath = "//button[contains(.,'Interview') and contains(@class,'tabs')]")
    public WebElement interviewTab;


    @FindBy(xpath = "//p-radiobutton[@ng-reflect-input-id='Screening']")
    private WebElement screeningRadio;

    @FindBy(xpath = "//p-radiobutton[@ng-reflect-input-id='Test']")
    private WebElement testRadio;

    @FindBy(xpath = "//p-radiobutton[@ng-reflect-input-id='Interview']")
    private WebElement interviewRadio;

    @FindBy(xpath = "//p-radiobutton[@ng-reflect-input-id='Offline Interview']")
    private WebElement offlineInterviewRadio;

    @FindBy(xpath = "//p-radiobutton[@ng-reflect-input-id='Offer']")
    private WebElement offerRadio;

    @FindBy(xpath = "//p-radiobutton[@ng-reflect-input-id='Onboarding']")
    private WebElement onboardingRadio;

    @FindBy(xpath = "//p-radiobutton[@ng-reflect-input-id='Others']")
    private WebElement othersRadio;


    @FindBy(xpath = "//button[@label='Add Stage']")
    private WebElement addStageButton;


    @FindBy(xpath = "//input[@id='stageName']")
    private WebElement stageNameInputField;

    @FindBy(xpath = "//button[@label='Save']")
    private WebElement saveStageButton;


    @FindBy(xpath = "//span[@role='combobox' and @aria-label='Documentation Form']/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement documentationFormDropdown;

    @FindBy(xpath = "//span[@role='combobox' and @aria-label='Test']/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement testDropdown;

    @FindBy(xpath = "//p[text()='Send Test Link']/following-sibling::div//span/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement TestLinkSearch;

    @FindBy(xpath = "//span[@role='combobox' and @aria-label='Feedback Form']/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement feedbackFormDropdown;

    @FindBy(xpath = "//p[text()='Send Interview Link']/following-sibling::div//span/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement interviewLinkSearch;

    @FindBy(xpath = "//p[text()='Send Interview Invite']/following-sibling::div//span/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement interviewInviteSearch;

    @FindBy(xpath = "//span[@role='combobox' and @aria-label='Offer Form']/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement offerFormDropdown;

    @FindBy(xpath = "//p[text()='Send Offer Link']/following-sibling::div//span/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement offerLinkSearch;

    // Shared search input used by all searchable dropdowns
    @FindBy(xpath = "//input[@role='searchbox']")
    private WebElement commonSearchInput;

    @FindBy(xpath = "//div[text()='Success']")
    private WebElement stageSuccessMsg;

    @FindBy(css = "span[role='combobox']")
    private WebElement itemsPerPageDropdown;

    @FindBy(xpath = "//input[@placeholder='Search Email']")
    private WebElement emailSearchInput;


    public void clickOnTab(String tabName) {
    String tabXpath = String.format("//button[contains(.,'%s') and contains(@class,'tabs')]", tabName);
    WebElement tab = driver.findElement(By.xpath(tabXpath));
    tab.click();
    }


    public void clickAddStage() {
        addStageButton.click();
    }

    public void createStage(String stageType, String stageName, String dropdownValue, String linkValue) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

     switch (stageType.toLowerCase()) {
        case "screening":
            wait.until(ExpectedConditions.elementToBeClickable(screeningRadio)).click();
            clickAndSelectDropdownValue(driver,documentationFormDropdown,dropdownValue);
            break;

        case "test":
            wait.until(ExpectedConditions.elementToBeClickable(testRadio)).click();
            clickAndSelectDropdownValue(driver,testDropdown,dropdownValue);

            ElementUtils.searchAndSelectFromSingleDropdown(
                    TestLinkSearch,
                    By.xpath("//input[@role='searchbox']"),
                    linkValue,
                    linkValue,
                    driver
            );
            break;

        case "interview":
            wait.until(ExpectedConditions.elementToBeClickable(interviewRadio)).click();
            clickAndSelectDropdownValue(driver, feedbackFormDropdown, dropdownValue);

            ElementUtils.searchAndSelectFromSingleDropdown(
                    interviewLinkSearch,
                    By.xpath("//input[@role='searchbox']"),
                    linkValue,
                    linkValue,
                    driver
            );
            break;

        case "offline interview":
            wait.until(ExpectedConditions.elementToBeClickable(offlineInterviewRadio)).click();
            clickAndSelectDropdownValue(driver,feedbackFormDropdown,dropdownValue);

            ElementUtils.searchAndSelectFromSingleDropdown(
                    interviewInviteSearch,
                    By.xpath("//input[@role='searchbox']"),
                    linkValue,
                    linkValue,
                    driver
            );
            break;

        case "offer":
            wait.until(ExpectedConditions.elementToBeClickable(offerRadio)).click();

            clickAndSelectDropdownValue(driver,offerFormDropdown,dropdownValue);

            ElementUtils.searchAndSelectFromSingleDropdown(
                    offerLinkSearch,
                    By.xpath("//input[@role='searchbox']"),
                    linkValue,
                    linkValue,
                    driver
            );
            break;

        case "onboarding":
            wait.until(ExpectedConditions.elementToBeClickable(onboardingRadio)).click();
            break;

        case "others":
            wait.until(ExpectedConditions.elementToBeClickable(othersRadio)).click();
            break;

        default:
            throw new IllegalArgumentException("Invalid stage type: " + stageType);
    }
    // Enter Stage Name
    stageNameInputField.clear();
    stageNameInputField.sendKeys(stageName);

    // Save Stage
    saveStageButton.click();
    Thread.sleep(2000);
}


    public boolean isStageCreatedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    try {
        return wait.until(ExpectedConditions.visibilityOf(stageSuccessMsg)).isDisplayed();
    } catch (TimeoutException e) {
        return false;
    }
}


    public void selectItemsPerPage(String visibleText) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Click the dropdown to open the options
    wait.until(ExpectedConditions.elementToBeClickable(itemsPerPageDropdown)).click();
    Thread.sleep(1000);

    // Wait for options to become visible
    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
        By.cssSelector("ul.p-dropdown-items li[role='option']")));

    // Loop and match the visible text exactly
    for (WebElement option : options) {
        String optionText = option.getText().trim();
        if (optionText.equals(visibleText)) {
            option.click();
                Thread.sleep(4000);

            return;
        }
    }

    throw new NoSuchElementException("Items per page option '" + visibleText + "' not found.");

    }


    public void selectCandidateByEmail(String email) throws InterruptedException {
    ElementUtils.selectCandidateFromList(driver, email);
}

    public void selectAllCandidates() {
    ElementUtils.selectAllCandidates(driver);
}


    public void searchCandidateByEmail(String email) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(emailSearchInput));

            emailSearchInput.clear();
            emailSearchInput.sendKeys(email);
            emailSearchInput.sendKeys(Keys.ENTER); // If Enter triggers the search

            // Optional: Wait for results to load
            Thread.sleep(1000); // or use wait for specific result row

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to search candidate by email: " + email, e);
        }
    }

    public void clickStageByName(String stageName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // This XPath matches the <p> tag containing the exact visible stage name before the <span>
        String xpath = "//div[contains(@class,'stage')]//p/text()[normalize-space(.)='" + stageName + "']/parent::*";

        WebElement stage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        stage.click();
    }


    public void clickStageMenuOption(String stageName, String option) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Open the menu
        String menuXpath = "//div[contains(@class,'stages-list')]//div[contains(@class,'stage') and .//p[contains(normalize-space(),'" + stageName + "')]]//em[contains(@class,'stageOptions')]";
        WebElement menuIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(menuXpath)));
        menuIcon.click();

        // 2. Click the desired option from popup
        String optionXpath = "//p[contains(@class,'stageOptions-overlay') and normalize-space()='" + option + "']";
        WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        optionElement.click();
    Thread.sleep(300);
    }

    public void renameStage( String newName) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Wait for the input field with placeholder 'Rename'
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Rename']")));
        input.clear();
        input.sendKeys(newName);
    Thread.sleep(500);
        // 2. Click the ✓ tick icon (first <em>)
        WebElement tickIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//em[contains(@class, 'pi-check') and contains(@class, 'rename-action_icon')]")));
        tickIcon.click();
        Thread.sleep(2000);

    }


}
