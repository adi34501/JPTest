package PageObjects;

import Utilities.CommonAction;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class YahooSearchPage {

    WebDriver driver;
    By feeds = By.xpath("//h3[@class='title']//span/parent::a[@aria-label]");

    @FindBy(xpath = "//div[@class='actions couple']/button[@name='reject']")
    WebElement popUpButton;




    public YahooSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public List<String> getFeedList() {
        CommonAction.waitForElement(driver, feeds);
        List<String> feedHeadings = new ArrayList<>();
        List<WebElement> feedsValues = driver.findElements(feeds);
        for (WebElement x : feedsValues) {
            feedHeadings.add(x.getText());
        }
        return feedHeadings;
    }

    public void performSearch(String articleTitle) {
        driver.get("https://search.yahoo.com/search?p=" + articleTitle);
        closePopup(popUpButton);
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.con-wizard")));
        try {
            if(element.isDisplayed()){
                popUpButton.click();
            }
        }
        catch (NoSuchElementException e){
            System.out.println("Span element not found.");
        }
    }
}