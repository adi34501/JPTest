package PageObjects;

import Utilities.CommonAction;
import BasePackage.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GuardianFeedPage extends BaseClass {

    @FindBy (xpath = "//h3[@class='fc-item__title']//a[not(contains(@href,'live'))]")
    WebElement  firstArticle;

    @FindBy (xpath = "//button[@title='Save and close']")
    WebElement  closeBtn;

    @FindBy (xpath = "//iframe[@id='sp_message_iframe_106842']")
    WebElement frame;

    @FindBy (xpath = "//iframe[@id='sp_message_iframe_801669']")
    WebElement frame2;

    @FindBy (xpath = "//button[@title='Manage or reject cookies']")
    WebElement  manageCookies;


    @FindBy (xpath = "//h3[@class='fc-item__title']//a[not(contains(@href,'live'))]")
    WebElement  article;
    @FindBy (xpath = "//aside[@data-gu-name='info']//span[@class='dcr-u0h1qy']")
    WebElement publishDate;
    @FindBy (xpath = "//aside[@data-gu-name='info']//a[@rel='author']")
    public WebElement author;

    public GuardianFeedPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String getArticleHeading(){
        CommonAction.waitForElement(driver,firstArticle);
        return firstArticle.getText();
    }

    public String getPublishDate(){
        CommonAction.switchFrame(driver,frame2,"sp_message_iframe_801669");
        CommonAction.click(manageCookies,driver);
        driver.switchTo().parentFrame();
        CommonAction.switchFrame(driver,frame,"sp_message_iframe_106842");
        CommonAction.click(closeBtn,driver);
        driver.switchTo().parentFrame();
        article.click();
        CommonAction.waitForElement(driver,publishDate);
        String Date = publishDate.getText().substring(4,15);
        return Date;
    }

    public String getAuthor(){
        CommonAction.switchFrame(driver,frame2,"sp_message_iframe_801669");
        CommonAction.click(manageCookies,driver);
        driver.switchTo().parentFrame();
        CommonAction.switchFrame(driver,frame,"sp_message_iframe_106842");
        CommonAction.click(closeBtn,driver);
        driver.switchTo().parentFrame();
        article.click();
        CommonAction.waitForElement(driver,author);
        return author.getText();
    }


}
