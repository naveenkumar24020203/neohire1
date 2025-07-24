package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Pattern;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class DateTimePickerUtils {

public static void selectDateTime(WebDriver driver, By triggerLocator,
                                  String year, String month, String day,
                                  int targetHour, int targetMinute, String targetAmPm) throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // STEP 1: Open date-time picker
    WebElement calendarTrigger = wait.until(ExpectedConditions.elementToBeClickable(triggerLocator));
    calendarTrigger.click();
    Thread.sleep(800);

    // STEP 2: Select YEAR
    WebElement yearButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.p-datepicker-year")));
    yearButton.click();
    Thread.sleep(300);

    int targetYear = Integer.parseInt(year);
    while (true) {
        List<WebElement> yearElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector("span.p-yearpicker-year")));

        boolean found = false;
        for (WebElement yearEl : yearElements) {
            String rawText = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].textContent;", yearEl);
            java.util.regex.Matcher m = Pattern.compile("\\b(\\d{4})\\b").matcher(rawText);
            if (m.find() && m.group(1).equals(String.valueOf(targetYear))) {
                yearEl.click();
                Thread.sleep(150);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.p-monthpicker-month")));
                found = true;
                break;
            }
        }

        if (found) break;

        String firstYearText = (String) ((JavascriptExecutor) driver)
            .executeScript("return arguments[0].textContent;", yearElements.get(0));
        java.util.regex.Matcher firstMatch = Pattern.compile("\\b(\\d{4})\\b").matcher(firstYearText);
        int firstYear = firstMatch.find() ? Integer.parseInt(firstMatch.group(1)) : 0;

        By navBtn = (targetYear < firstYear) ? By.cssSelector("button.p-datepicker-prev") : By.cssSelector("button.p-datepicker-next");
        wait.until(ExpectedConditions.elementToBeClickable(navBtn)).click();
        Thread.sleep(400);
    }

    // STEP 3: Select MONTH
    String[] shortMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    String targetMonthShort = month.matches("\\d+")
        ? shortMonths[Integer.parseInt(month) - 1]
        : month;

    List<WebElement> months = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
        By.cssSelector("span.p-monthpicker-month")));
    for (WebElement m : months) {
        if (m.getText().trim().equalsIgnoreCase(targetMonthShort)) {
            m.click();
            Thread.sleep(100);
            break;
        }
    }

    // STEP 4: Select DATE (this will CLOSE the calendar)
    List<WebElement> dayCells = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
        By.cssSelector("table.p-datepicker-calendar td")));
    for (WebElement cell : dayCells) {
        String cellClass = cell.getAttribute("class");
        if (cellClass.contains("p-datepicker-other-month") || cellClass.contains("p-disabled")) continue;

        WebElement span = cell.findElement(By.tagName("span"));
        if (span.getText().trim().equals(day)) {
            span.click(); // Will close the picker
            Thread.sleep(300);
            break;
        }
    }

    // STEP 5: REOPEN picker to set time
    calendarTrigger = wait.until(ExpectedConditions.elementToBeClickable(triggerLocator));
    calendarTrigger.click();
    Thread.sleep(700);

    // STEP 6: Set AM/PM
    WebElement amPmContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-ampm-picker")));
    WebElement amPmSpan = amPmContainer.findElement(By.xpath(".//span"));
    String currentAmPm = amPmSpan.getText().trim().toUpperCase();
    String targetUpper = targetAmPm.toUpperCase();

    if (!currentAmPm.equals(targetUpper)) {
        amPmContainer.findElement(By.xpath(".//button[@aria-label='" + targetAmPm.toLowerCase() + "']")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(amPmSpan, targetUpper));
    }

    // STEP 7: Set HOUR
    WebElement hourContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".p-hour-picker")));
    WebElement hourSpan = hourContainer.findElement(By.xpath(".//span"));
    wait.until(driver1 -> !hourSpan.getText().trim().isEmpty());
    int currentHour = Integer.parseInt(hourSpan.getText().trim());

    while (currentHour != targetHour) {
        if (targetHour > currentHour) {
            hourContainer.findElement(By.xpath(".//button[@aria-label='Next Hour']")).click();
        } else {
            hourContainer.findElement(By.xpath(".//button[@aria-label='Previous Hour']")).click();
        }
        Thread.sleep(50);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(hourSpan, String.valueOf(currentHour))));
        currentHour = Integer.parseInt(hourSpan.getText().trim());
    }

    // STEP 8: Set MINUTE
    WebElement minuteContainer = driver.findElement(By.cssSelector(".p-minute-picker"));
    WebElement minuteSpan = minuteContainer.findElement(By.xpath(".//span"));
    int currentMinute = Integer.parseInt(minuteSpan.getText().trim());

    while (currentMinute != targetMinute) {
        if (targetMinute > currentMinute) {
            minuteContainer.findElement(By.xpath(".//button[@aria-label='Next Minute']")).click();
        } else {
            minuteContainer.findElement(By.xpath(".//button[@aria-label='Previous Minute']")).click();
        }
        Thread.sleep(50);
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(minuteSpan, String.valueOf(currentMinute))));
        currentMinute = Integer.parseInt(minuteSpan.getText().trim());
    }

    Thread.sleep(500); // Final settle
}



public static void parseAndSelectDate(SelectDateFunction dateFunction, String dateTime) throws InterruptedException {

    String[] parts = dateTime.split(" ");
    String datePart = parts[0]; // "2025-07-25"
    String timePart = parts[1]; // "10:30"
    String amPm = parts[2];     // "AM" or "PM"

    LocalDate date = LocalDate.parse(datePart);
    String year = String.valueOf(date.getYear());
    String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH); // e.g., "Jul"
    String day = String.valueOf(date.getDayOfMonth());

    String[] time = timePart.split(":");
    int hour = Integer.parseInt(time[0]);
    int minute = Integer.parseInt(time[1]);


    dateFunction.select(year, month, day, hour, minute, amPm);
}

}
