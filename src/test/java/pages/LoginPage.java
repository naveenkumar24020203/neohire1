package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    // Constructor
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this); 
    }

    // Elements
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement emailAddress;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@class='verify-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//label[text()='Manage Events']")
    private WebElement manageEvent;

    // Actions
    public void enterEmail(String email) {
        emailAddress.clear();
        emailAddress.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() throws InterruptedException {
        Thread.sleep(500);
        loginButton.click();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public boolean isManageEventTextDisplayed(){
        return manageEvent.isDisplayed();
    }
}
