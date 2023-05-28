package Tests.Scenarios;

import BasePackage.BaseClass;
import PageObjects.GoogleSearchPage;
import PageObjects.GuardianFeedPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateViaGoogleTest extends BaseClass {

    private GuardianFeedPage guardianFeedPage;
    private GoogleSearchPage googleSearchPage;
    private String ArticleText;

    @Test(priority = -1,description = "Verifies that the first Guardian news article is valid.")
    public void validateArticle(){
        guardianFeedPage = new GuardianFeedPage(driver);
        ArticleText = guardianFeedPage.getArticleHeading();
        googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.performSearch(ArticleText);
        Assert.assertTrue(googleSearchPage.performFactChecking(ArticleText),"Article is not valid");
    }

    @Test(description = "Verifies that right page is displayed")
    public void verifyTitle(){
        Assert.assertEquals("News | The Guardian",driver.getTitle());
    }

    @Test(description = "Verifies that right page is not displayed")
    public void verifyTitle_Negative() {
        Assert.assertNotEquals("News | The Guardian test", driver.getTitle());
    }

    @Test(description = "Verifies the article publish date is displayed")
    public void verifyArticlePublishDate() {
        guardianFeedPage = new GuardianFeedPage(driver);
        String publishDate = guardianFeedPage.getPublishDate();
        Assert.assertNotNull("Publish date is not displayed", publishDate);
    }

    @Test(description = "Verifies that the author name is displayed")
    public void verifyAuthor() {
        String author = new GuardianFeedPage(driver).getAuthor();
        Assert.assertNotNull("Auther name is not displayed",author);
    }

    @Test(description = "Verifies that the article is fetched successfully")
    public void verifyArticleIsFetched(){
        guardianFeedPage = new GuardianFeedPage(driver);
        Assert.assertTrue(guardianFeedPage.getArticleHeading()!=null,"Not able to fetch article");
    }

    @Test(description = "Verifies that the search page opens after performing a search")
    public void verifyOpenSearchPage(){
        new GoogleSearchPage(driver).performSearch(ArticleText);
        Assert.assertTrue( driver.getCurrentUrl().contains("google.com/search"),"Not on Google Search Page");
    }
}
