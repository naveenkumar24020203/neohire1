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

    // ========== STEP 1: Click trigger ==========
    WebElement calendarTrigger = wait.until(ExpectedConditions.elementToBeClickable(triggerLocator));
    calendarTrigger.click();
    Thread.sleep(1000);
    // ========== STEP 2: SET TIME ==========
    WebElement hourContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".p-hour-picker")));
    WebElement hourSpan = hourContainer.findElement(By.xpath(".//span"));

    // Wait until hourSpan text is not empty
    wait.until(driver1 -> !hourSpan.getText().trim().isEmpty());

    int currentHour = Integer.parseInt(hourSpan.getText().trim());


    while (currentHour != targetHour) {
        if (targetHour > currentHour) {
            hourContainer.findElement(By.xpath(".//button[@aria-label='Next Hour']")).click();
            Thread.sleep(50);

        } else {
            hourContainer.findElement(By.xpath(".//button[@aria-label='Previous Hour']")).click();
            Thread.sleep(50);

        }
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(hourSpan, String.valueOf(currentHour))));
        currentHour = Integer.parseInt(hourSpan.getText().trim());
    }

    WebElement minuteContainer = driver.findElement(By.cssSelector(".p-minute-picker"));
    WebElement minuteSpan = minuteContainer.findElement(By.xpath(".//span"));
    int currentMinute = Integer.parseInt(minuteSpan.getText().trim());

    while (currentMinute != targetMinute) {
        if (targetMinute > currentMinute) {
            minuteContainer.findElement(By.xpath(".//button[@aria-label='Next Minute']")).click();
            Thread.sleep(50);

        } else {
            minuteContainer.findElement(By.xpath(".//button[@aria-label='Previous Minute']")).click();
            Thread.sleep(50);

        }
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(minuteSpan, String.valueOf(currentMinute))));
        currentMinute = Integer.parseInt(minuteSpan.getText().trim());
    }

    WebElement amPmContainer = driver.findElement(By.cssSelector(".p-ampm-picker"));
    WebElement amPmSpan = amPmContainer.findElement(By.xpath(".//span"));
    String currentAmPm = amPmSpan.getText().trim().toUpperCase();
    String targetUpper = targetAmPm.toUpperCase();

    if (!currentAmPm.equals(targetUpper)) {
        amPmContainer.findElement(By.xpath(".//button[@aria-label='" + targetAmPm.toLowerCase() + "']")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(amPmSpan, targetUpper));
    }

    // ========== STEP 3: SELECT YEAR ==========
    WebElement yearButton = wait.until(ExpectedConditions.elementToBeClickable(
        By.cssSelector("button.p-datepicker-year")
    ));
    yearButton.click();
    Thread.sleep(500);



int targetYear = Integer.parseInt(year); 



// ========== STEP 3: SELECT YEAR ==========
while (true) {
    // Refetch year elements each time to avoid stale reference
    List<WebElement> yearElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
        By.cssSelector("span.p-yearpicker-year")
    ));

    boolean found = false;
    for (WebElement yearEl : yearElements) {
        // Use JavaScript to get raw textContent (handles nested spans or duplicates)
        String rawText = (String) ((JavascriptExecutor) driver)
            .executeScript("return arguments[0].textContent;", yearEl);

        // Extract first 4-digit number using regex
        String cleaned = "";
        java.util.regex.Matcher m = Pattern.compile("\\b(\\d{4})\\b").matcher(rawText);
        if (m.find()) {
            cleaned = m.group(1);
        }

        // System.out.print("[" + cleaned + "] ");

        // If match, click the year and break
        if (cleaned.equals(String.valueOf(targetYear))) {
            yearEl.click();
            Thread.sleep(150);

            // Confirm month picker is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("span.p-monthpicker-month")
            ));
            found = true;
            break;
        }
    }

    if (found) break;

    // Retry: Navigate to next/prev decade
    // Get clean numeric value from first year in view
    String firstYearText = (String) ((JavascriptExecutor) driver)
        .executeScript("return arguments[0].textContent;", yearElements.get(0));
    java.util.regex.Matcher firstMatch = Pattern.compile("\\b(\\d{4})\\b").matcher(firstYearText);
    int firstYear = firstMatch.find() ? Integer.parseInt(firstMatch.group(1)) : 0;


    if (targetYear < firstYear) {
        WebElement prevBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.p-datepicker-prev")
        ));
        prevBtn.click();
    } else {
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button.p-datepicker-next")
        ));
        nextBtn.click();
    }

    Thread.sleep(400); // Give time for next decade to load
}

                                      



String targetMonthShort;

if (month.matches("\\d+")) {
    int monthNumber = Integer.parseInt(month);
    if (monthNumber < 1 || monthNumber > 12) {
        throw new IllegalArgumentException("❌ Invalid month number: " + monthNumber);
    }

    String[] shortMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    targetMonthShort = shortMonths[monthNumber - 1];
} else {
    targetMonthShort = month;
}

// ========== STEP 4: SELECT MONTH ==========
List<WebElement> months = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
    By.cssSelector("span.p-monthpicker-month")
));
// for (WebElement m : months) {
//     System.out.println("    ➜ " + m.getText().trim());
// }

boolean found = false;
for (WebElement m : months) {
    if (m.getText().trim().equalsIgnoreCase(targetMonthShort)) {
        m.click();
        Thread.sleep(100); // optional delay
        found = true;
        break;
    }
}

if (!found) {
    throw new NoSuchElementException("❌ Month '" + targetMonthShort + "' not found in the calendar UI.");
}


    // ========== STEP 5: SELECT DATE ==========
    List<WebElement> dayCells = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
        By.cssSelector("table.p-datepicker-calendar td")
    ));

    for (WebElement cell : dayCells) {
        String cellClass = cell.getAttribute("class");

        if (cellClass.contains("p-datepicker-other-month") || cellClass.contains("p-disabled")) {
            continue;
        }

        WebElement span = cell.findElement(By.tagName("span"));
        if (span.getText().trim().equals(day)) {
            span.click();
            Thread.sleep(150);

            break;
        }
    }
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
