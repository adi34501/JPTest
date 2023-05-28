package PageObjects;

import Utilities.CommonAction;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class GoogleSearchPage {

    private WebDriver driver;
    private By feeds = By.cssSelector("div.g h3");

    @FindBy(css = "div.KxvlWc")
    WebElement popup;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getFeedList() {
        CommonAction.waitForElement(driver, feeds);
        List<WebElement> feedElements = driver.findElements(feeds);
        List<String> feedHeadings = new ArrayList<>();
        for (WebElement element : feedElements) {
            feedHeadings.add(element.getText());
        }
        return feedHeadings;
    }

    public void performSearch(String articleTitle) {
        driver.get("https://www.google.com/search?q=" + articleTitle);
        closePopup(popup);
    }

    public Boolean performFactChecking(String text) {
        Boolean results = false;
        List<String> relatedFeeds = new ArrayList<>();
        String[] targetWords = text.split(" ");
        Set<String> targetSet = new HashSet<>(Arrays.asList(targetWords));
        List<String> feedHeadings = getFeedList();
        for (String heading : feedHeadings) {
            if(CommonAction.checkIfNewsRelated(heading, targetSet)){
                relatedFeeds.add(heading);
            }
            }
        if (relatedFeeds.size() >= 2) {
            results = CommonAction.hoaxCheck(relatedFeeds);
        }
        return results;
    }

    public void closePopup(WebElement element){
        CommonAction.waitForElement(driver,popup);
        try {
            if(element.isDisplayed()){
                element.findElement(By.xpath("//button[@id='L2AGLb']")).click();
            }
        }
        catch (NoSuchElementException e){
            System.out.println("Span element not found.");
        }

    }
}