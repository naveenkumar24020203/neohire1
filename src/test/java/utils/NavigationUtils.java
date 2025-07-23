package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// In utils/NavigationUtils.java
public class NavigationUtils {

    public static void navigateToSideMenu(WebDriver driver, String menuName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> sideMenuButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector(".sidebar-icons button")
        ));

        boolean clicked = false;

        for (WebElement button : sideMenuButtons) {
            String label = button.getAttribute("ng-reflect-content");

            if (label != null && label.trim().equalsIgnoreCase(menuName)) {
                wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                clicked = true;
                break;
            }
        }

        if (!clicked) {
            throw new NoSuchElementException("Menu with name '" + menuName + "' not found in sidebar.");
        }
    }
}

