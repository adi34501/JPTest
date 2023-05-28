package BasePackage;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;

public class APIBaseClass extends BaseClass{


    @BeforeClass
    public void setUpRest(){
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://api.newscatcherapi.com/").
                setBasePath("v2/").
                setContentType("application/json").
                addHeader("x-api-key","spZt_hGrO6SbofyX_Jg3K7UUdP57iPKIN4wiVz5yBpI").
                build();
    }



}
