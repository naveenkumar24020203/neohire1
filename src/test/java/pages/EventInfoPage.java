package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DateTimePickerUtils;
import utils.ElementUtils;

import static utils.ElementUtils.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


public class EventInfoPage {

    private WebDriver driver;

    private WebDriverWait wait;

    public EventInfoPage(WebDriver driver) {
        this.driver = driver;
            PageFactory.initElements(driver, this);  // ← This line is essential
                    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));


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

    @FindBy(xpath = "//p-multiselect[@formcontrolname='documentCohortLocation']//div[contains(@class, 'p-multiselect-trigger')]")
    private WebElement jobLocationMultiSelectTrigger;

    @FindBy(xpath = "//p-multiselect[@formcontrolname='cohortJobLocation']//div[contains(@class, 'p-multiselect-trigger')]")
    private WebElement sendOfferJobLocationMultiSelectTrigger;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='documentCohortJobRole']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement jobRoleSelectTrigger;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='cohortJobRole']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement sendOfferJobRoleSelectTrigger;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='offerDeadline']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement sendOfferDeadlineSelectTrigger;
    // Document POC dropdown trigger
    @FindBy(xpath = "//p-dropdown[@formcontrolname='documentPOC']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement docPocSelectTrigger;

    // Document Deadline dropdown trigger
    @FindBy(xpath = "//p-dropdown[@formcontrolname='documentDeadline']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement documentDeadlineSelectTrigger;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='offerDeadline']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement offerDeadlineSelectTrigger;

    @FindBy(xpath = "//p[text()='Send Interview Invite']/following-sibling::div//span/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement interviewInviteSearch;

    @FindBy(xpath = "//span[@role='combobox' and @aria-label='Offer Form']/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement offerFormDropdown;

    @FindBy(xpath = "//p[text()='Send Offer Link']/following-sibling::div//span/following::div[@aria-label='dropdown trigger'][1]")
    private WebElement offerLinkSearch;

    private static final By commonSearchInput = By.xpath("//input[@role='searchbox']");

    @FindBy(xpath = "//div[text()='Success']")
    private WebElement stageSuccessMsg;

    @FindBy(css = "span[role='combobox']")
    private WebElement itemsPerPageDropdown;

    @FindBy(xpath = "//input[@placeholder='Search Email']")
    private WebElement emailSearchInput;

    @FindBy(xpath = "//input[@role='searchbox']")
    private WebElement searchInDropDown;

    @FindBy(xpath = "//textarea[@id='remarks']")
    private WebElement remarksInputField;

    @FindBy(xpath = "//button[normalize-space()='Move Stage']")
    private WebElement moveStageBtn;

    @FindBy(xpath = "//button[normalize-space()='Send Email']")
    private WebElement sendMailBtn;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='template']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement EmailTemplateDropdown;

    @FindBy(xpath = "//button[normalize-space()='Shortlist']")
    private WebElement shortlistButton;

    @FindBy(xpath = "//button[normalize-space()='Pending']")
    private WebElement pendingButton;

    @FindBy(xpath = "//button[normalize-space()='Rejected']")
    private WebElement rejectedButton;

    @FindBy(xpath = "//button[normalize-space()='Waitlist']")
    private WebElement waitlistButton;

    @FindBy(xpath = "//button[normalize-space()='Not Eligible']")
    private WebElement notEligibleButton;

    @FindBy(xpath = "//button[normalize-space()='Send Documentation Link']")
    private WebElement sendDocumentationLinkButton;

    @FindBy(xpath = "//button[normalize-space()='Mail Pending']")
    private WebElement mailPendingButton;

    @FindBy(xpath = "//button[normalize-space()='Submission Pending']")
    private WebElement submissionPendingButton;

    @FindBy(xpath = "//button[normalize-space()='Submitted']")
    private WebElement submittedButton;

    @FindBy(xpath = "//button[normalize-space()='Submitted Partially']")
    private WebElement submittedPartiallyButton;

    @FindBy(xpath = "//button[normalize-space()='Approved Partially']")
    private WebElement approvedPartiallyButton;

    @FindBy(xpath = "//button[normalize-space()='All Clear']")
    private WebElement allClearButton;

    @FindBy(xpath = "//button[normalize-space()='Send Test Link']")
    private WebElement sendTestLinkButton;

    @FindBy(xpath = "//button[normalize-space()='Slot Sent']")
    private WebElement slotSentButton;

    @FindBy(xpath = "//button[normalize-space()='No Show']")
    private WebElement noShowButton;

    @FindBy(xpath = "//button[normalize-space()='Send Interview Link']")
    private WebElement sendInterviewLinkButton;

    @FindBy(xpath = "//button[normalize-space()='Send Interview Venue']")
    private WebElement sendInterviewVenueButton;

    @FindBy(xpath = "//button[normalize-space()='Joined']")
    private WebElement joinedButton;

    @FindBy(xpath = "//button[normalize-space()='Not Joined']")
    private WebElement notJoinedButton;

    @FindBy(xpath = "//button[normalize-space()='Not Joining']")
    private WebElement notJoiningButton;

    @FindBy(xpath = "//button[normalize-space()='Joining Confirmed']")
    private WebElement joiningConfirmedButton;

    @FindBy(xpath = "//button[normalize-space()='Yet To Join']")
    private WebElement yetToJoinButton;

    @FindBy(xpath = "//button[normalize-space()='Send Offer']")
    private WebElement sendOfferButton;

    @FindBy(xpath = "//button[normalize-space()='Offer Pending']")
    private WebElement offerPendingButton;

    @FindBy(xpath = "//button[normalize-space()='On Hold']")
    private WebElement onHoldButton;

    @FindBy(xpath = "//button[normalize-space()='Future Candidate']")
    private WebElement futureCandidateButton;

    @FindBy(xpath = "//button[normalize-space()='Offered']")
    private WebElement offeredButton;

    @FindBy(xpath = "//button[normalize-space()='Offer Revision']")
    private WebElement offerRevisionButton;

    @FindBy(xpath = "//button[normalize-space()='Offer Accepted']")
    private WebElement offerAcceptedButton;

    @FindBy(xpath = "//button[normalize-space()='Offer Rejected']")
    private WebElement offerRejectedButton;

    @FindBy(xpath = "//label[contains(normalize-space(),'Document POC Role')]/preceding-sibling::p-dropdown//div[contains(@class,'p-dropdown-trigger')]")
    private WebElement documentationPocRoleDropdownTrigger;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='startTime']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement startTimeDropdownTrigger;

    @FindBy(xpath = "//p-dropdown[@formcontrolname='duration']//div[contains(@class, 'p-dropdown-trigger')]")
    private WebElement durationDropdownTrigger;

    @FindBy(xpath = "//input[@formcontrolname='venue']")
    private WebElement interviewVenueInput;

    @FindBy(xpath = "//textarea[@formcontrolname='content']")
    private WebElement contentInput;

    @FindBy(xpath = "//input[@formcontrolname='subjectTitle']")
    private WebElement subjectInput;

    @FindBy(xpath = "//table//thead//input[@type='checkbox']") // Select all or specific as needed
    private WebElement firstCandidateCheckbox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitStatusBtn;


    @FindBy(xpath = "//button[normalize-space()='Rules']")
    private WebElement rulesTabBtn;

@FindBy(xpath = "//p-dropdown[contains(@class,'ruleSet-trigger')]//div[contains(@class,'p-dropdown-trigger')]")
private WebElement ruleWhenDropdown;

@FindBy(xpath = "(//label[@for='20']/preceding-sibling::p-radiobutton//div[@data-pc-name='radiobutton'])[1]")
private WebElement matchingConditionRadio;

@FindBy(xpath = "(//label[@for='20']/preceding-sibling::p-radiobutton//div[@data-pc-name='radiobutton'])[2]")
private WebElement forAllRadio;

@FindBy(xpath = "//div[contains(@class,'conditionSet-individual')]//p-dropdown[1]//div[contains(@class,'p-dropdown-trigger')]")
private WebElement fieldDropdownTrigger;

@FindBy(xpath = "(//div[contains(@class,'conditionSet-individual')]//span[@role='combobox'])[2]/following-sibling::div[@role='button']")
private WebElement operatorDropdownTrigger;

@FindBy(xpath = "//div[contains(@class,'conditionSet-individual')]//input[contains(@placeholder,'Enter value')]")
private WebElement conditionInputField;

@FindBy(xpath = "//button[normalize-space(text())='Positive']")
private WebElement positiveConditionButton;

@FindBy(xpath = "//button[normalize-space(text())='Negative']")
private WebElement negativeConditionButton;

@FindBy(xpath = "//div[contains(@class, 'conditionSet-individual')]//em[contains(@class, 'addIcon-plus')]")
private WebElement addConditionIcon;

@FindBy(xpath = "//div[contains(@class, 'conditionSet-individual')]//em[contains(@class, 'removeIcon-minus')]")
private WebElement removeConditionIcon;

@FindBy(xpath = "(//div[not(contains(@class,'conditionSet-individual'))]//em[contains(@class, 'addIcon-plus')])[2]")
private WebElement addActionIcon;

@FindBy(xpath = "(//div[not(contains(@class,'conditionSet-individual'))]//em[contains(@class, 'removeIcon-minus')])[2]")
private WebElement removeActionIcon;

@FindBy(xpath = "//p-dropdown[contains(@class,'ruleSet-dropdown')]//div[contains(@class,'p-dropdown-trigger')]")
private List<WebElement> actionDropdownTriggers;


@FindBy(xpath = "(//p-dropdown[contains(@class,'ruleSet-dropdown')]//div[contains(@class,'p-dropdown-trigger')])[2]")
private WebElement ruleTemplateDropdown;

@FindBy(xpath = "//button[normalize-space()='Save']")
private WebElement saveRuleButton;



















public void selectActions(List<String> actions) throws InterruptedException {
    for (int i = 0; i < actions.size(); i++) {
        if (i >= actionDropdownTriggers.size()) {
            addActionIcon.click(); // Click '+' to add more if needed
        }
        WebElement actionDropdown = actionDropdownTriggers.get(i);
        ElementUtils.clickAndSelectDropdownValue(driver, actionDropdown, actions.get(i));
    }
}




public void selectWhenTrigger(String triggerOption) throws InterruptedException {
    ElementUtils.clickAndSelectDropdownValue(driver, ruleWhenDropdown, triggerOption);
}

public void selectConditionType(boolean isMatchingCondition) {
    if (isMatchingCondition) {
        matchingConditionRadio.click();
    } else {
        forAllRadio.click();
    }
}


public void enterMatchingCondition(String field, String operator, String value) throws InterruptedException {
    ElementUtils.clickAndSelectDropdownValue(driver, fieldDropdownTrigger, field);
    ElementUtils.clickAndSelectDropdownValue(driver, operatorDropdownTrigger, operator);
    conditionInputField.clear();
    conditionInputField.sendKeys(value);
}


public void setConditionMode(String mode) {
    if (mode.equalsIgnoreCase("positive")) {
        positiveConditionButton.click();
    } else {
        negativeConditionButton.click();
    }
}


public void clickAddCondition() {
    addConditionIcon.click();
}

public void clickRemoveCondition() {
    removeConditionIcon.click();
}





public void selectRuleTemplate(String templateName) throws InterruptedException {
    ElementUtils.clickAndSelectDropdownValue(driver, ruleTemplateDropdown, templateName);
}


public void clickSaveRule() {
    saveRuleButton.click();
}









public void createRule(
    String whenOption,
    boolean isMatching,
    String conditionField,
    String conditionOperator,
    String conditionValue,
    String conditionMode, // "positive" or "negative"
    List<String> actions,
    String template // Optional, pass null if not needed
) throws InterruptedException {

    selectWhenTrigger(whenOption);
    selectConditionType(isMatching);

    if (isMatching) {
        enterMatchingCondition(conditionField, conditionOperator, conditionValue);
    }

    setConditionMode(conditionMode);
    selectActions(actions);

    if (template != null && !template.isEmpty()) {
        selectRuleTemplate(template);
    }

    clickSaveRule();
}






























































































   
    public void clickStageByName(String stageName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void clickMoveStageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(moveStageBtn)).click();
    }

    public void selectStageToMove(String stageName) {
        // Wait for the popup to appear
        By stageOption = By.xpath("//div[contains(@class,'stageList')]//p[normalize-space(text())='" + stageName + "']");

        WebElement targetStage = wait.until(ExpectedConditions.elementToBeClickable(stageOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetStage);
        targetStage.click();
}

    public void enterRemarks(String remark) {
        remarksInputField.clear();
        remarksInputField.sendKeys(remark);
    }

    public void submitStageMove() {
        wait.until(ExpectedConditions.elementToBeClickable(submitStatusBtn)).click();
    }

    public void clickSendMailButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendMailBtn)).click();
    }

    public void selectEmailTemplate(String template) throws InterruptedException {
                ElementUtils.searchAndSelectFromDropdown(EmailTemplateDropdown,searchInDropDown,template,driver);

    }

    public void enterSubject(String subject) {
        wait.until(ExpectedConditions.visibilityOf(subjectInput)).sendKeys(subject);
    }

    public void enterContent(String content) {
        wait.until(ExpectedConditions.visibilityOf(contentInput)).sendKeys(content);
    }

    public void clickSendMailFinal() {
        wait.until(ExpectedConditions.elementToBeClickable(submitStatusBtn)).click();
    }

public void clickStatusButton(String label) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // XPath with case-insensitive match using translate
    String xpath = "//button[translate(normalize-space(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '" 
                   + label.toLowerCase() + "']";

    By buttonLocator = By.xpath(xpath);

    try {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(buttonLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({inline: 'center'});", button);
        Thread.sleep(300);

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        System.out.println("✅ Clicked button: " + label);
    } catch (TimeoutException e) {
        throw new NoSuchElementException("❌ Could not find status button with label: " + label);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}




    public void clickSubmitStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(submitStatusBtn)).click();
    }
   

   
    public void clickSendTestLinkButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendTestLinkButton)).click();
    }

    public void clickSendInterviewLinkButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendInterviewLinkButton)).click();
    }
    public void clickSendInterviewVenueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendInterviewVenueButton)).click();
    }

    private void clickSendDocumentationLinkButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendDocumentationLinkButton)).click();
}
    private void clickSendOfferButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sendOfferButton)).click();

}


private void selectDocumentationJobRole(String role) throws InterruptedException {
    ElementUtils.clickAndSelectDropdownValue(driver,jobRoleSelectTrigger,role);
    Thread.sleep(400);
}

private void selectDocumentationJobLocation(String location) throws InterruptedException {
    ElementUtils.searchAndSelectFromMultiDropdown(
        jobLocationMultiSelectTrigger,
        commonSearchInput,
        new String[] { location },
        driver
    );
        Thread.sleep(400);

}

private void selectOfferJobRole(String role) throws InterruptedException {
    ElementUtils.clickAndSelectDropdownValue(driver,sendOfferJobRoleSelectTrigger,role);
        Thread.sleep(400);

}

private void selectOfferJobLocation(String location) throws InterruptedException {
    ElementUtils.searchAndSelectFromMultiDropdown(
        sendOfferJobLocationMultiSelectTrigger,
        commonSearchInput,
        new String[] { location },
        driver
    );
        Thread.sleep(400);

}

private void selectDocumentDeadline(String string) throws InterruptedException {
ElementUtils.clickAndSelectDropdownValue(driver, documentDeadlineSelectTrigger, string);
    Thread.sleep(400);

}

private void selectDocPoc(String name) throws InterruptedException {
        ElementUtils.searchAndSelectFromDropdownAfterClick(driver, docPocSelectTrigger, name);

}

private void selectDocPocRole(String role) throws InterruptedException {
    ElementUtils.searchAndSelectFromDropdownAfterClick(driver, documentationPocRoleDropdownTrigger, role);
}


public void selectInterviewDate(String date) throws InterruptedException {
    // Format: dd-MM-yyyy
    String[] parts = date.split("-");
    String day = String.valueOf(Integer.parseInt(parts[0]));   // "02" → "2"
    String month = String.valueOf(Integer.parseInt(parts[1])); // "08" → "8"
    String year = parts[2];

    By dobCalendarTrigger = By.xpath("//button[@aria-label='Choose Date']");
    DateTimePickerUtils.selectDateOnly(driver, dobCalendarTrigger, year, month, day);
    Thread.sleep(1000);
}

    
public void selectStartTime(String time) throws InterruptedException {
    ElementUtils.clickAndSelectDropdownValue(driver, startTimeDropdownTrigger, time);  // time = "08:30"
        Thread.sleep(400);

}
private void selectDuration(String duration) throws InterruptedException {
    String normalized = normalizeDuration(duration);
    ElementUtils.clickAndSelectDropdownValue(driver, durationDropdownTrigger, normalized);
        Thread.sleep(400);

}

public void clickSendInterviewFinal() {
        wait.until(ExpectedConditions.elementToBeClickable(submitStatusBtn)).click();
}

 private void clickSendOfferFinal() {
        wait.until(ExpectedConditions.elementToBeClickable(submitStatusBtn)).click();

}
private void clickSendDocumentationFinal() {
        wait.until(ExpectedConditions.elementToBeClickable(submitStatusBtn)).click();

}
 private void clickSendInterviewVenueFinal() {
        wait.until(ExpectedConditions.elementToBeClickable(submitStatusBtn)).click();
}
private void enterVenue(String venue) {
    interviewVenueInput.clear();
    interviewVenueInput.sendKeys(venue);

}
private void selectOfferDeadline(String deadline) throws InterruptedException {
   ElementUtils.clickAndSelectDropdownValue(driver, offerDeadlineSelectTrigger, deadline);
       Thread.sleep(400);


}



public void performStageAction( String action, Map<String, String> optionalParams) throws InterruptedException {
    switch (action.toLowerCase()) {

        // ACTION: Move Stage
        case "move stage":
            clickMoveStageButton();
            selectStageToMove(optionalParams.get("targetStage"));
            if (optionalParams.containsKey("remarks")) {
                enterRemarks(optionalParams.get("remarks"));
            }
            submitStageMove();
            break;

        // ACTION: Send Mail
        case "send mail":
            clickSendMailButton();
            selectEmailTemplate(optionalParams.get("template"));
            Thread.sleep(500);
            clickSendMailFinal();
            Thread.sleep(200);
            break;

        // ACTION: Send Documentation Link (Screening)
        case "send documentation link":
            clickSendDocumentationLinkButton();
            selectDocumentationJobLocation(optionalParams.get("jobLocation"));
            selectDocumentDeadline(optionalParams.get("deadline"));
            selectDocumentationJobRole(optionalParams.get("jobRole"));
            selectDocPocRole(optionalParams.get("docPocRole"));
            selectDocPoc(optionalParams.get("docPoc"));
            selectEmailTemplate(optionalParams.get("template"));
            Thread.sleep(500);
            clickSendDocumentationFinal();
            break;


        // ACTION: Send Test Link (Test)
        case "send test link":
            clickSendTestLinkButton(); // You can extend if it has config
            break;

        // ACTION: Send Interview Link (Interview)
        case "send interview link":
            clickSendInterviewLinkButton();
            selectInterviewDate(optionalParams.get("date"));
            selectStartTime(optionalParams.get("startTime"));
            selectDuration(optionalParams.get("duration"));
            clickSendInterviewFinal();
            break;

        // ACTION: Send Interview Venue (Offline Interview)
        case "send interview venue":
            clickSendInterviewVenueButton();
            selectInterviewDate(optionalParams.get("date"));
            selectStartTime(optionalParams.get("startTime"));
            selectDuration(optionalParams.get("duration"));
            enterVenue(optionalParams.get("venue"));
            clickSendInterviewVenueFinal();
            break;

        // ACTION: Send Offer (Offer stage)
        case "send offer":
            clickSendOfferButton();
            selectOfferJobLocation(optionalParams.get("jobLocation"));
            selectEmailTemplate(optionalParams.get("template"));

            selectOfferJobRole(optionalParams.get("jobRole"));
            selectOfferDeadline(optionalParams.get("offerDeadline"));
            Thread.sleep(500);

            // Skip entering subject/content if pre-filled
            if (optionalParams.containsKey("subject")) {
                enterSubject(optionalParams.get("subject"));
            }
            if (optionalParams.containsKey("content")) {
                enterContent(optionalParams.get("content"));
            }

            clickSendOfferFinal();
            break;


        // DEFAULT ACTIONS – Status clicks + remarks + submit
        default:
            clickStatusButton(action);
            if (optionalParams.containsKey("remarks")) {
                enterRemarks(optionalParams.get("remarks"));
            }
            clickSubmitStatus();
    }
}




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
            Thread.sleep(500);
            clickAndSelectDropdownValue(driver,documentationFormDropdown,dropdownValue);
            break;

        case "test":
            wait.until(ExpectedConditions.elementToBeClickable(testRadio)).click();
            Thread.sleep(500);

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
            Thread.sleep(500);
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
            Thread.sleep(500);
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
            Thread.sleep(500);
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
            Thread.sleep(500);
            break;

        case "others":
            wait.until(ExpectedConditions.elementToBeClickable(othersRadio)).click();
            Thread.sleep(500);
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


private String normalizeDuration(String input) {
    input = input.trim().toLowerCase();

    // Case: "15m" → "0h 15m"
    if (input.matches("^\\d{1,2}m$")) {
        return "0h " + input.replace("m", "m");
    }

    // Case: "1h", "2h" → "1h 0m"
    if (input.matches("^\\d{1,2}h$")) {
        return input.replace("h", "h 0m");
    }

    // Case: already in "1h 15m" format
    if (input.matches("^\\d{1,2}h\\s\\d{1,2}m$")) {
        return input;
    }

    throw new IllegalArgumentException("Unsupported duration format: " + input);
}


}
