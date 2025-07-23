package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class ElementUtils {
    private static WebDriver driver;

    public ElementUtils(WebDriver driver) {
        ElementUtils.driver = driver;
    }

    // Select from dropdown by visible text
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = driver.findElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    // Select from dropdown by index
    public void selectDropdownByIndex(By locator, int index) {
        WebElement dropdown = driver.findElement(locator);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    // Select multiple values from multi-select dropdown (like NeoHire tag pickers)
    public void selectMultipleFromDropdown(By containerLocator, List<String> values) {
        WebElement container = driver.findElement(containerLocator);
        for (String value : values) {
            WebElement option = container.findElement(By.xpath(".//*[text()='" + value + "']"));
            if (!option.getAttribute("class").contains("selected")) {
                option.click();
            }
        }
    }

    // Wait for element to be visible
    public WebElement waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Get visible text from an element
    public String getElementText(WebElement element) {
        return element.getText().trim();
    }

    // Select from dropdown with placeholder
    public static void selectCustomDropdownOption(WebDriver driver, String placeholder, String optionToSelect) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement dropdownInput = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//input[@placeholder='" + placeholder + "']")
    ));
    dropdownInput.click();  // Triggers dropdown

    // Wait for dropdown options to be visible
    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
        By.cssSelector(".dropdown-option")
    ));

    boolean found = false;

    for (WebElement option : options) {
        if (option.getText().trim().equalsIgnoreCase(optionToSelect)) {
            option.click();
            found = true;
            break;
        }
    }

    if (!found) {
        throw new NoSuchElementException("Option '" + optionToSelect + "' not found for dropdown with placeholder '" + placeholder + "'");
    }
}

    public static void searchAndSelectFromDropdown(WebElement dropdownClickElement, WebElement searchInputElement, String valueToSelect, WebDriver driver) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // 1. Click the dropdown to open options
    wait.until(ExpectedConditions.elementToBeClickable(dropdownClickElement)).click();

    // 2. Type the value to search
    wait.until(ExpectedConditions.elementToBeClickable(searchInputElement)).sendKeys(valueToSelect);
    Thread.sleep(200);

    // Locate and click exact matching option
    By optionLocator = By.xpath("//li[contains(@class,'p-dropdown-item') and @aria-label='" + valueToSelect.trim() + "']");
    wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();

}

public static void searchAndSelectFromMultiDropdown(
        WebElement dropdownClickElement,
        By searchInputLocator,
        String[] valuesToSelect,
        WebDriver driver) throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    System.out.println("Total values to select: " + valuesToSelect.length);

    for (String value : valuesToSelect) {
        boolean selected = false;
        int attempts = 0;

        while (!selected && attempts < 2) {
            try {
                // Click to open the dropdown
                wait.until(ExpectedConditions.elementToBeClickable(dropdownClickElement)).click();
                Thread.sleep(200); // slight delay to avoid DOM lag

                // Type the value
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputLocator));
                input.clear();
                input.sendKeys(value.trim());

                // Click matching option
                By optionLocator = By.xpath("//li[contains(@class,'p-multiselect-item') and @aria-label='" + value.trim() + "']");
                wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();

                System.out.println("✅ Clicked on: " + value.trim());
                selected = true;
            } catch (TimeoutException e) {
                System.out.println("⏳ Retrying to open dropdown for: " + value.trim());
                attempts++;
            }
        }

        if (!selected) {
            System.out.println("❌ Failed to select: " + value.trim());
        }
    }
}






}

