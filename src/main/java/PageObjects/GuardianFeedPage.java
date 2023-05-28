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


    @FindBy (xpath = "//a[@class='u-faux-block-link__overlay js-headline-text' and not(contains(@href,'live'))]")
    WebElement  article;

    By publishDate = By.xpath("//address[@aria-label='Contributor info']/following-sibling::div");


    @FindBy (xpath = "//address[@aria-label='Contributor info']//a")
    public WebElement author;

    public GuardianFeedPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public String getArticleHeading(){
        CommonAction.waitForElement(driver,firstArticle);
        return firstArticle.getText();
    }

    public String getPublishDate() throws NullPointerException{
        CommonAction.switchFrame(driver,frame2,"sp_message_iframe_801669");
        CommonAction.click(manageCookies,driver);
        driver.switchTo().parentFrame();
        CommonAction.switchFrame(driver,frame,"sp_message_iframe_106842");
        CommonAction.click(closeBtn,driver);
        driver.switchTo().parentFrame();
        article.click();
        String Date = CommonAction.getText(publishDate,driver).substring(4,15);
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
        return CommonAction.getText(author,driver);
    }


}
