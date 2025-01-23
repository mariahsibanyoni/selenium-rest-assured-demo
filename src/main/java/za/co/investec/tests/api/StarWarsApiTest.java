package za.co.investec.tests.api;


import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import com.aventstack.extentreports.Status;
import za.co.investec.base.BaseAPITest;


public class StarWarsApiTest extends BaseAPITest {

    private static final String BASE_URL = "https://swapi.dev/api";

    @Test
    public void testR2D2SkinColor() {
        test.info("Starting API test for R2D2's skin color");

        try {
            Response response = given()
                    .when()
                    .get(BASE_URL + "/people/")
                    .then()
                    .statusCode(200)
                    .extract().response();

            // Log the response
            test.info("API Response: " + response.asPrettyString());

            // Verify R2D2's skin color
            String skinColor = response.path("results.find { it.name == 'R2-D2' }.skin_color");
            test.info("R2-D2's skin color: " + skinColor);

            org.testng.Assert.assertEquals(skinColor, "white, blue", "R2-D2's skin color should be white and blue");
            test.log(Status.PASS, "Successfully verified R2-D2's skin color is white and blue");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "API test failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Unexpected error during API test: " + e.getMessage());
            throw e;
        }
    }
}
