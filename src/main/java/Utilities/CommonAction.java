package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CommonAction {


    public static void click(WebElement ele, WebDriver driver){
        CommonAction.waitForElement(driver,ele);
        ele.click();
    }

    public static String getText(By ele, WebDriver driver){
        CommonAction.waitForElement(driver,ele);
        return driver.findElement(ele).getText();
    }

    public static String getText(WebElement ele, WebDriver driver){
        CommonAction.waitForElement(driver,ele);
        return ele.getText();
    }


    public static Boolean ElementIsDisplayed(WebElement ele){
        return ele.isDisplayed();
    }

    public static WebElement getShadowDOM(WebElement element,WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot",element);
        return shadowDom;
    }

    public static void waitForElement(WebDriver driver,WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public static void waitForFrame(WebDriver driver,String name){
        WebDriverWait wait = new WebDriverWait(driver,10);;
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
    }

    public static void waitForElement(WebDriver driver,By ele){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
    }


    public static void switchFrame(WebDriver driver,WebElement ele,String id){
        CommonAction.waitForElement(driver,ele);
        driver.switchTo().frame(id);
    }

    public static void CheckPopUp(WebElement element){
        System.out.println(element.getClass());
        if(element.isDisplayed()){
            element.findElement(By.xpath("//button[@class='automat-1nicaxs']")).click();
        }
    }

    public static boolean hoaxCheck(List<String> relatedFeeds){
        String[] keywords = {"hoax", "debunked", "false", "unverified"};
        for (String heading : relatedFeeds) {
            for (String keyword : keywords) {
                if (!heading.contains(keyword)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkIfNewsRelated(String heading, Set<String> targetSet) {
        int matchingCount = 0;
        String[] words = heading.split(" ");
        for (String word : words) {
            if (targetSet.contains(word)) {
                matchingCount++;
            }
        }
        double matchPercentage = (double) matchingCount / targetSet.size() * 100;
        if (matchPercentage >= 30.0) {
            return true;
        }
        return false;
    }

}
