package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import jakarta.mail.Transport; 

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.List;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;


public class ElementUtils {
    private static WebDriver driver;

    public ElementUtils(WebDriver driver) {
        ElementUtils.driver = driver;
    }

    // Select from dropdown by visible text
    // In ElementUtils.java
   public static void clickAndSelectDropdownValue(WebDriver driver, WebElement dropdown, String valueToSelect) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Step 1: Click the dropdown
    wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();

// ✅ New wait here — wait for any dropdown option to be visible
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='option']")));
Thread.sleep(600); // Optional fine-tune delay

    // Step 2: Build option locator dynamically
    By optionLocator = By.xpath("//li[@role='option' and normalize-space()='" + valueToSelect.trim() + "']");


    // Step 3: Try to click, fallback to JS click if intercepted
    boolean selected = wait.until(driver1 -> {
        try {
            WebElement option = driver1.findElement(optionLocator);
            if (option.isDisplayed() && option.isEnabled()) {
                try {
                    option.click();
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver1).executeScript("arguments[0].scrollIntoView(true);", option);
                    ((JavascriptExecutor) driver1).executeScript("arguments[0].click();", option);
                }
                return true;
            }
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            System.out.println("Dropdown option not found or stale: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while selecting dropdown: " + e.getMessage());
        }
        return false;
    });

    // Step 4: Final validation
    if (!selected) {
        throw new TimeoutException("Unable to select value: " + valueToSelect + " from dropdown after 10 seconds.");
    }
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

    public static void searchAndSelectFromDropdown(
        WebElement dropdownClickElement,
        WebElement searchInputElement,
        String valueToSelect,
        WebDriver driver
) throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Thread.sleep(300); // Let any overlay settle

    // Step 1: Open dropdown safely
    try {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownClickElement)).click();
        Thread.sleep(1000); // Let any overlay settle

    } catch (ElementClickInterceptedException e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownClickElement);
    }

    // Step 2: Search inside dropdown
    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInputElement));
    input.clear();
    input.sendKeys(valueToSelect.trim());
    Thread.sleep(500); // Let matching options load

    // Step 3: Build dynamic locator
    By optionLocator = By.xpath("//li[contains(@class,'p-dropdown-item') and @aria-label='" + valueToSelect.trim() + "']");
    //li[contains(@class,'p-dropdown-item') and @aria-label='change email OTP mail']

    // Step 4: Try clicking the desired option (JS fallback included)
    try {
        wait.until(driver1 -> {
            try {
                List<WebElement> options = driver1.findElements(optionLocator);
                for (WebElement option : options) {
                    if (option.isDisplayed() && option.isEnabled()) {
                        try {
                            option.click();
                        } catch (ElementClickInterceptedException e) {
                            ((JavascriptExecutor) driver1).executeScript("arguments[0].click();", option);
                        }
                        return true;
                    }
                }
                return false; // Retry if not clickable
            } catch (StaleElementReferenceException e) {
                return false; // Retry if stale
            }
        });
    } catch (TimeoutException e) {
        throw new NoSuchElementException("Option '" + valueToSelect + "' not found or not clickable using locator: " + optionLocator);
    }
}


    public static void searchAndSelectFromMultiDropdown(
        WebElement dropdownClickElement,
        By searchInputLocator,
        String[] valuesToSelect,
        WebDriver driver) throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    for (String value : valuesToSelect) {
        boolean selected = false;
        int attempts = 0;

        while (!selected && attempts < 3) {
            try {
                // Step 1: Click dropdown
                wait.until(ExpectedConditions.elementToBeClickable(dropdownClickElement)).click();
                Thread.sleep(300);

                // Step 2: Search
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputLocator));
                input.clear();
                input.sendKeys(value.trim());
                Thread.sleep(500); // Let options render

                // Step 3: Locate matching options (by aria-label or visible text)
                List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//li[contains(@class,'p-multiselect-item') and not(contains(@class,'p-disabled'))]")
                ));

                for (WebElement option : options) {
                    String label = option.getAttribute("aria-label").trim();
                    if (label.equalsIgnoreCase(value.trim())) {
                        // Scroll and click
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
                        Thread.sleep(200);

                        try {
                            option.click();
                        } catch (ElementClickInterceptedException e) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                        }

                        selected = true;
                        break;
                    }
                }

                if (!selected) {
                    throw new NoSuchElementException("Value not found in dropdown: " + value);
                }

            } catch (StaleElementReferenceException | TimeoutException e) {
                attempts++;
                Thread.sleep(300); // slight delay before retry
            }
        }

        if (!selected) {
            throw new NoSuchElementException("Failed to select dropdown value: " + value);
        }
    }
}


    public static void searchAndSelectFromSingleDropdown(
        WebElement dropdownClickElement,
        By searchInputLocator,
        String userInput,
        String optionTextToMatch,
        WebDriver driver) throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    boolean selected = false;
    int attempts = 0;

    while (!selected && attempts < 2) {
        try {
            // Open dropdown
            wait.until(ExpectedConditions.elementToBeClickable(dropdownClickElement)).click();
            Thread.sleep(200); // minor delay

            // Type into search input
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputLocator));
            input.clear();
            input.sendKeys(userInput);

            // Dynamic option locator (you can modify class as needed)
            String dynamicXpath = String.format("//li[contains(@class,'p-dropdown-item') and @aria-label='%s']", optionTextToMatch);
            By optionLocator = By.xpath(dynamicXpath);

            // Wait and click on matching option
            wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
            selected = true;

        } catch (TimeoutException e) {
            attempts++;
        }
    }

    if (!selected) {
        throw new TimeoutException("Could not select: " + optionTextToMatch);
    }
}


    public static void selectCandidateFromList(WebDriver driver, String candidateEmail) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

    try {
        if (candidateEmail == null || candidateEmail.trim().isEmpty()) {
            // Bulk select all via table header
            WebElement headerCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//thead//div[contains(@class, 'p-checkbox-box')]")));
            if (!headerCheckbox.isSelected()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", headerCheckbox);
            }
        } else {
            // Locate the row using the email text
            String email = candidateEmail.trim();
            WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tbody/tr[td[contains(string(),'" + email + "')]]")
            ));

            // Locate the checkbox inside the first <td>
            WebElement checkbox = row.findElement(By.xpath("//tr//div[contains(@class, 'p-checkbox-box')]"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);
            wait.until(ExpectedConditions.elementToBeClickable(checkbox));

            if (!checkbox.isSelected()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
            }
        }

    } catch (TimeoutException e) {
        throw new RuntimeException("❌ Timeout: Candidate with email '" + candidateEmail + "' not found.", e);
    } catch (Exception e) {
        throw new RuntimeException("❌ Error selecting candidate checkbox: " + e.getMessage(), e);
    }

    Thread.sleep(300);
}


    public static void selectAllCandidates(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    try {
        // Wait for the header checkbox to be clickable
        WebElement headerCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//thead//div[contains(@class, 'p-checkbox-box')]")));

        // If not already selected, click it
        if (!headerCheckbox.isSelected()) {
            headerCheckbox.click();
        }

        // Optional: wait briefly to allow UI update
        Thread.sleep(1000);

    } catch (Exception e) {
        throw new RuntimeException("❌ Failed to select all candidates using header checkbox: " + e.getMessage(), e);
    }
}


    public static void selectEventCheckboxByName(String eventName, WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    String checkboxXpath = "(//table//tr[td[contains(normalize-space(),'" + eventName + "')]]//td[1]//div[contains(@class,'p-checkbox')])[2]";
    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkboxXpath)));
    checkbox.click();
}


public static void searchAndSelectFromDropdownAfterClick(
        WebDriver driver,
        WebElement dropdownTrigger,
        String valueToSelect
) throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    dropdownTrigger.click();
    Thread.sleep(500);

    WebElement searchInput = wait.until(
        ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@role='searchbox']"))
    );
    searchInput.clear();
    searchInput.sendKeys(valueToSelect.trim());
    Thread.sleep(400);

    By optionLocator = By.xpath("//li[contains(@class,'p-dropdown-item') and @aria-label='" + valueToSelect.trim() + "']");
    wait.until(driver1 -> {
        try {
            List<WebElement> options = driver1.findElements(optionLocator);
            for (WebElement option : options) {
                if (option.isDisplayed() && option.isEnabled()) {
                    try {
                        option.click();
                    } catch (ElementClickInterceptedException e) {
                        ((JavascriptExecutor) driver1).executeScript("arguments[0].click();", option);
                    }
                    return true;
                }
            }
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    });
}











public static boolean clickAndSelectDropdownValueWithRetry(WebDriver driver, WebElement dropdown, String valueToSelect) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    try {
        dropdown.click();
        Thread.sleep(400); // allow dropdown to expand

        By optionLocator = By.xpath("//li[contains(@class,'p-dropdown-item') and normalize-space()='" + valueToSelect + "']");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
        return true;
    } catch (Exception e) {
        System.out.println("⚠️ Dropdown option '" + valueToSelect + "' not found or not clickable: " + e.getMessage());
        return false;
    }
}



public static void sendEmailWithAttachment(String toEmail, String subject, String body, String attachmentPath) {
    final String fromEmail = "naveeniamneo112@gmail.com";
    final String password = "lgzbeyqgfmwtccnw"; // app password with no spaces
 
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
 
 Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(fromEmail, password);
            }
        });
 
   try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(attachmentPath));
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("📧 PDF report emailed to: " + toEmail);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to send email: " + e.getMessage(), e);
        }
    }
 
 
 
 
public static void runPuppeteerAndEmailReport(String nodeScriptPath, String pdfPath, String toEmail) {
    try {
        System.out.println("🌐 Launching Puppeteer to generate PDF...");
 
        // Prepare Node.js Puppeteer command
        ProcessBuilder pb = new ProcessBuilder("node", nodeScriptPath);
        pb.redirectErrorStream(true); // Merge stderr with stdout
 
        Process process = pb.start();
 
        // Read and print Puppeteer script output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("📄 " + line);
            }
        }
 
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("✅ Puppeteer PDF generation successful: " + pdfPath);
 
            // Send email with the generated PDF
            ElementUtils.sendEmailWithAttachment(
                toEmail,
                "📄 Automation Test Report",
                "Hi,\n\nPlease find attached the PDF report for the recent test execution.",
                pdfPath
            );
 
            System.out.println("📬 PDF Report sent successfully to: " + toEmail);
        } else {
            System.err.println("❌ Puppeteer script failed with exit code: " + exitCode);
        }
 
    } catch (IOException | InterruptedException e) {
        System.err.println("⚠️ Failed to convert/send report: " + e.getMessage());
        e.printStackTrace();
    }
}
 
 
 
 
 

}

