package DriverSingleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.Paths;

public final class DriverClass {

    private static WebDriver driver;
    private DriverClass(){};

    public static WebDriver getDriver(String browser) {

        if (driver == null) {
            if (browser.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver","src/main/resources/Drivers/chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
            return driver;
        }
        else {
            return driver;
        }
    }


    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
