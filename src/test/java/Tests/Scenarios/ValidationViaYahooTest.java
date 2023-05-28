package Tests.Scenarios;

import BasePackage.BaseClass;
import PageObjects.GuardianFeedPage;
import PageObjects.YahooSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationViaYahooTest extends BaseClass {

    private GuardianFeedPage guardianFeedPage;
    private YahooSearchPage yahooSearchPage;
    private String articleText;

    @Test(description = "Verifies that the first Guardian news article is valid.")
    public void validateArticle() {
        guardianFeedPage = new GuardianFeedPage(driver);
        articleText = guardianFeedPage.getArticleHeading();
        yahooSearchPage = new YahooSearchPage(driver);
        yahooSearchPage.performSearch(articleText);
        Assert.assertTrue(yahooSearchPage.performFactChecking(articleText),"Article is not valid");
    }

    @Test(description = "Verifies that the correct page title is displayed.")
    public void verifyTitle() {
        Assert.assertEquals(driver.getTitle(),"News | The Guardian","Not on the news Page of Guardian");
    }

    @Test(description = "Verifies that the article is fetched successfully.")
    public void verifyArticleIsFetched() {
        guardianFeedPage = new GuardianFeedPage(driver);
        Assert.assertTrue( guardianFeedPage.getArticleHeading() != null,"Not able to fetch article");
    }

    @Test(description = "Verifies that the search page is opened.")
    public void verifyOpenSearchPage() {
        new YahooSearchPage(driver).performSearch(articleText);
        Assert.assertTrue( driver.getCurrentUrl().contains("yahoo"),"Not on Yahoo Search Page");
    }
}

