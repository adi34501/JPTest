package Tests.Scenarios;

import BasePackage.APIBaseClass;
import BasePackage.BaseClass;
import PageObjects.GuardianFeedPage;
import com.aventstack.extentreports.gherkin.model.ScenarioOutline;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
public class ValidationViaAPITest extends APIBaseClass {

    private String articleText;
    private String regexPattern = "\\[|]|/|\\\\|%5B|%5D|%2F|%5C|:|%3A|\\^|%5E";

    @Test(description = "Verifies the response code of the API")
    public void verifyResponseCode() {
        GuardianFeedPage guardianFeedPage = new GuardianFeedPage(driver);
        articleText = guardianFeedPage.getArticleHeading().replaceAll(regexPattern, " ");
        System.out.println(articleText);

        given()
                .queryParam("q", articleText)
                .when()
                .get("search")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

    @Test(description = "Verifies the presence of related articles in the API response")
    public void verifyArticle() {
        GuardianFeedPage guardianFeedPage = new GuardianFeedPage(driver);
        articleText = guardianFeedPage.getArticleHeading().replaceAll(regexPattern, " ");
        System.out.println(articleText);

        List<Map<String, ?>> feedMap = null;
        Response response = given()
                .queryParam("q", articleText)
                .when()
                .get("search")
                .then()
                .extract().response();
        response.prettyPrint();

        try {
            feedMap = response.jsonPath().getList("articles");
            Assert.assertTrue(feedMap.size() >= 2, "Article is not valid");
        } catch (NullPointerException e) {
            System.out.println("The API didn't have related news");
        }
    }
}

