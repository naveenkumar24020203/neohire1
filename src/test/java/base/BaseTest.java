package base;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public static void initializeDriver(String browser) {
    switch (browser.toLowerCase()) {
        case "chrome":
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            break;

        case "firefox":
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            break;

        case "edge":
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            break;

        default:
            throw new IllegalArgumentException("❌ Unsupported browser: " + browser);
    }

    logger.info(browser + " browser launched successfully.");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
    ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%'");
}


    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            logger.info("Closing the browser...");
            driver.quit();
            logger.info("Browser closed.");
        }
    }
}
