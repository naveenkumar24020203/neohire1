package pages;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import utils.ElementUtils;
import utils.NavigationUtils;

import java.time.Duration;

public class RolePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public RolePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@label='New Role']")
    private WebElement createRoleBtn;

    @FindBy(xpath = "//input[@formcontrolname='roleName']")
    private WebElement roleNameField;

    @FindBy(xpath = "//input[@formcontrolname='roleType']")
    private WebElement roleTypeField;

    @FindBy(xpath = "//input[@formcontrolname='remoteJob']")
    private WebElement workModeField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(xpath = "//input[@formcontrolname='country']")
    private WebElement countryName;

    @FindBy(xpath = "//input[@formcontrolname='state']")
    private WebElement stateName;

    @FindBy(xpath = "//input[@formcontrolname='city']")
    private WebElement cityName;

    @FindBy(xpath = "//input[@formcontrolname='businessUnit']")
    private WebElement businessUnitField;
    
    @FindBy(xpath = "//*[text()='Success']")
    private WebElement successMsg;



    public void navigateToRolesPage() {
        NavigationUtils.navigateToSideMenu(BaseTest.getDriver(), "Manage Job Roles");
    }

    public void clickCreateRoleButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createRoleBtn)).click();
    }

    public void enterRoleName(String name) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(roleNameField)).clear();
        roleNameField.sendKeys(name);

    }

    public void selectRoleType(String roleType) throws InterruptedException {
    ElementUtils.selectCustomDropdownOption(driver, "Select role type", roleType);
    Thread.sleep(200);
    }
    
    public void selectWorkMode(String workMode) throws InterruptedException {
    ElementUtils.selectCustomDropdownOption(driver, "Select work mode", workMode);
    Thread.sleep(200);
    }

    public void selectCountry(String country) throws InterruptedException{
        ElementUtils.selectCustomDropdownOption(driver, "Search country...", country);
    Thread.sleep(200);
    }

    public void selectState(String state)throws InterruptedException{
        ElementUtils.selectCustomDropdownOption(driver, "Search state...", state);
    Thread.sleep(200);
    }

    public void selectCity(String city)throws InterruptedException{
        ElementUtils.selectCustomDropdownOption(driver, "Search city...", city);
    Thread.sleep(500);
    }

    public void enterBusinessUnit(String businessUnit)throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOf(businessUnitField)).clear();
        businessUnitField.sendKeys(businessUnit);
    Thread.sleep(500);
    }

    public void clickSaveButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        Thread.sleep(200);
    }

    public boolean isSuccessMessageVisible() {
    try {
        return wait.until(ExpectedConditions.visibilityOf(successMsg)).isDisplayed();
    } catch (TimeoutException e) {
        return false;
    }
}

}
