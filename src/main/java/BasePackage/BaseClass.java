package BasePackage;

import DriverSingleton.DriverClass;
import Reporting.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    protected static Properties properties;
    protected WebDriver driver;

    @BeforeSuite
    public void Config(){
        loadConfig();
    }

    @BeforeMethod
    public void setUp(){
        driver = DriverClass.getDriver(properties.getProperty("browser"));
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }


    public void loadConfig(){
        properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Configuration/config.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverClass.closeDriver();
    }

}
