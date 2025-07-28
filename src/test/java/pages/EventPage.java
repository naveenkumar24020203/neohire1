package pages;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import utils.DateTimePickerUtils;
import utils.ElementUtils;
import utils.NavigationUtils;

import java.time.Duration;
import java.util.List;

public class EventPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public EventPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ---------- ELEMENTS ----------
    @FindBy(xpath = "//button[@label='New Event']")
    private WebElement createEventBtn;

    @FindBy(xpath = "//input[@formcontrolname='eventTitle']")
    private WebElement eventTitleField;

    @FindBy(xpath = "(//label[text()='Job Role ']/following::div[@class='p-multiselect-trigger'])[1]")
    private WebElement jobRoleDropdown;

    @FindBy(xpath = "(//label[text()='Institution Name ']/following::div[@class='p-multiselect-trigger'])[1]")
    private WebElement institutionNameDropdown;

    @FindBy(xpath = "//input[@formcontrolname='institutionAddress']")
    private WebElement institutionAddress;

    @FindBy(xpath = "(//label[text()='Event Start Date ']/following::button[@aria-label='Choose Date'])[1]")
    private WebElement eventStartDate;

    @FindBy(xpath = "(//label[text()='Event End Date ']/following::button[@aria-label='Choose Date'])[1]")
    private WebElement eventEndDate;

    @FindBy(xpath = "//label[normalize-space()='Work Experience']/following::input[contains(@class, 'p-inputnumber-input')][1]")
    private WebElement workExpField;

    @FindBy(xpath = "//p-inputnumber[@formcontrolname='minSalary']//input[contains(@class, 'p-inputnumber-input')]")
    private WebElement minSalaryField;

    @FindBy(xpath = "//p-inputnumber[@formcontrolname='maxSalary']//input[contains(@class, 'p-inputnumber-input')]")
    private WebElement maxSalaryField;

    @FindBy(xpath = "//input[@role='searchbox']")
    private WebElement searchInDropDown;

    @FindBy(xpath = "(//label[text()='Event Category ']/following::div[@aria-label='dropdown trigger'])[1]")
    private WebElement eventCategoryDropdown;

    @FindBy(xpath = "(//label[text()='Event Type ']/following::div[@aria-label='dropdown trigger'])[1]")
    private WebElement eventTypeDropdown;

    @FindBy(xpath = "(//label[text()='Registration Form ']/following::div[@aria-label='dropdown trigger'])[1]")
    private WebElement registrationFormDropdown;

    @FindBy(xpath = "(//label[text()='Email Template ']/following::div[@aria-label='dropdown trigger'])[1]")
    private WebElement emailTemplateDropdown;

    @FindBy(xpath = "(//label[text()='Registration Start Date ']/following::button[@aria-label='Choose Date'])[1]")
    private WebElement regStartDate;

    @FindBy(xpath = "(//label[text()='Registration End Date ']/following::button[@aria-label='Choose Date'])[1]")
    private WebElement regEndDate;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(xpath = "//*[text()='Success']")
    private WebElement successMsg;

    @FindBy(css = "input.searchBar-input")
    private WebElement searchInput;

    @FindBy(css = "tbody.p-datatable-tbody")
    private WebElement tableBody;

    @FindBy(css = "tbody.p-datatable-tbody tr")
    private List<WebElement> eventListRows;

    @FindBy(css = "span.p-dropdown-label[role='combobox']")
    private WebElement paginationDropdown;

    @FindBy(css = "ul.p-dropdown-items")  // Container for dropdown list
    private WebElement dropdownList;

    @FindBy(css = "span[role='combobox']")
    private WebElement itemsPerPageDropdown;

    @FindBy(xpath = "//button[normalize-space()='Delete Event']")
    private WebElement deleteEventButton;
    // ---------- ACTIONS ----------

    public void navigateToEventsPage() {
        NavigationUtils.navigateToSideMenu(BaseTest.getDriver(), "Manage Events");
    }

    public void clickCreateEventButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createEventBtn)).click();
    }

    public void enterEventTitle(String title) {
        wait.until(ExpectedConditions.visibilityOf(eventTitleField)).sendKeys(title);
    }

    public void selectJobRole(String roles) throws InterruptedException {
    String[] roleArray = roles.split(",");

    ElementUtils.searchAndSelectFromMultiDropdown(
        jobRoleDropdown,                                // WebElement to open dropdown
        By.xpath("//input[@role='searchbox']"),         // search box locator inside the dropdown
        roleArray, driver
    );
}

    public void selectEventStartDate(String year, String month, String day, int hour, int minute, String amPm) throws InterruptedException {
        By trigger = By.xpath("(//label[text()='Event Start Date ']/following::button[@aria-label='Choose Date'])[1]");
        DateTimePickerUtils.selectDateTime(driver, trigger, year, month, day, hour, minute, amPm);
    }

    public void selectEventEndDate(String year, String month, String day, int hour, int minute, String amPm) throws InterruptedException {
        By trigger = By.xpath("(//label[text()='Event End Date ']/following::button[@aria-label='Choose Date'])[1]");
        DateTimePickerUtils.selectDateTime(driver, trigger, year, month, day, hour, minute, amPm);
    }

    public void enterWorkExperience(String exp) {
        workExpField.sendKeys(exp);
    }

    public void enterMinSalary(String minSalary) {
        minSalaryField.sendKeys(minSalary);
    }

    public void enterMaxSalary(String maxSalary) {
        maxSalaryField.sendKeys(maxSalary);
    }

    public void selectRegistrationForm(String form) throws InterruptedException {
        ElementUtils.searchAndSelectFromDropdown(registrationFormDropdown,searchInDropDown,form,driver);
    }

    public void selectEmailTemplate(String template) throws InterruptedException {
        ElementUtils.searchAndSelectFromDropdown(emailTemplateDropdown,searchInDropDown,template,driver);
    }

    public void selectEventCategory(String category) throws InterruptedException {
        ElementUtils.searchAndSelectFromDropdown(eventCategoryDropdown,searchInDropDown,category,driver);
    }

    public void selectEventType(String type) throws InterruptedException {
        ElementUtils.searchAndSelectFromDropdown(eventTypeDropdown,searchInDropDown,type,driver);
    }

    public void selectRegistrationStartDate(String year, String month, String day, int hour, int minute, String amPm) throws InterruptedException {
        By trigger = By.xpath("(//label[text()='Registration Start Date ']/following::button[@aria-label='Choose Date'])[1]");
        DateTimePickerUtils.selectDateTime(driver, trigger, year, month, day, hour, minute, amPm);
    }

    public void selectRegistrationEndDate(String year, String month, String day, int hour, int minute, String amPm) throws InterruptedException {
        By trigger = By.xpath("(//label[text()='Registration End Date ']/following::button[@aria-label='Choose Date'])[1]");
        DateTimePickerUtils.selectDateTime(driver, trigger, year, month, day, hour, minute, amPm);
    }
    
    public void selectInstitutionName(String names) throws InterruptedException {
    String[] nameArray = names.split(",");

    ElementUtils.searchAndSelectFromMultiDropdown(
        institutionNameDropdown,                             // dropdown WebElement
        By.xpath("//input[@role='searchbox']"),              // search input locator
        nameArray, driver                                     // list of values & driver
    );
}

   public void enterInstitutionAddress(String address) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    try {
        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", institutionAddress);

        // Wait until visible and interactable
        WebElement input = wait.until(ExpectedConditions.visibilityOf(institutionAddress));

        // Clear and type
        input.clear();
        input.sendKeys(address);

    } catch (ElementClickInterceptedException e) {
        // Fallback: try sending keys via JS (not recommended unless input is totally blocked)
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", institutionAddress, address);
    }
}



   public void clickSave() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    try {
        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", saveBtn);

        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));

        // Try normal click
        saveBtn.click();

    } catch (ElementClickInterceptedException e) {
        // Fallback to JS click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
    }
}
public boolean isEventCreatedSuccessfully() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(successMsg)).isDisplayed();
    } catch (TimeoutException e) {
        return false;
    }
}


    public void searchEvent(String eventName) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Thread.sleep(200);

    wait.until(ExpectedConditions.visibilityOf(searchInput));
    searchInput.clear();
    searchInput.sendKeys(eventName);
    Thread.sleep(200);

    // Wait for search result to appear (i.e., the event cell becomes visible)
    String resultXpath = "//table//td[normalize-space(text())='" + eventName + "']";
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(resultXpath)));
}


    public boolean isEventDisplayedInTable(String expectedEventName) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(tableBody));
    List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

    for (WebElement row : rows) {
        List<WebElement> columns = row.findElements(By.tagName("td"));
        if (columns.size() >= 2) {
            String actualEventName = columns.get(1).getText().trim();
            if (actualEventName.equals(expectedEventName)) { // Exact match (case-sensitive)
                return true;
            }
        }
    }
    return false;
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


    public void clickEvent(String eventName) {
    WebDriver driver = BaseTest.getDriver();

    String xpath = "//table//td[normalize-space(text())='" + eventName + "']";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement eventCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    eventCell.click();
}


public void deleteEvent(String eventName) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Select the checkbox for the event
    ElementUtils.selectEventCheckboxByName(eventName, driver);

    // Wait and click Delete button
    wait.until(ExpectedConditions.elementToBeClickable(deleteEventButton)).click();
    // Wait and click Confirm button in confirmation popup
    // WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(
    //     By.xpath("//button[normalize-space()='Confirm']")
    // ));
    // confirmDelete.click();
}



public void cloneEvent(String eventName) {
    ElementUtils.selectEventCheckboxByName(eventName, driver);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement cloneBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Clone']")));
    cloneBtn.click();

    WebElement confirmClone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Confirm']")));
    confirmClone.click();
}

}
