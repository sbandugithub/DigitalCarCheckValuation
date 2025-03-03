package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class CarTestBase {
    protected static WebDriver driver;

    public static void setupDriver() {
        String browser = PropertiesManager.getProperty("browser").toLowerCase();
        WebDriverManager manager;

        switch (browser) {
            case "chrome":
                manager = WebDriverManager.chromedriver();
                driver = new ChromeDriver();
                break;
            case "firefox":
                manager = WebDriverManager.firefoxdriver();
                driver = new FirefoxDriver();
                break;
            case "edge":
                manager = WebDriverManager.edgedriver();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        manager.setup();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Integer.parseInt(PropertiesManager.getProperty("timeout"))));
    }

    public void resetDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset for the next test
        }
    }

}