package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static final Integer TIMEOUT_IN_SECONDS = 5;
    private static WebDriver driver;

    public static WebDriver getDriver() {

        if(driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
            return driver;
        }

        return driver;
    }

    public static void shutDownDriver() {
        driver.quit();
        driver = null;
    }
}
