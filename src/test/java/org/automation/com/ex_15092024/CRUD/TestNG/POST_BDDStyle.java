package org.automation.com.ex_15092024.CRUD.TestNG;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class POST_BDDStyle {
    @Test
    public void postBDDStyle() {

        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON).log().all()
                .body(payload)
                .when().post().then().log().all().statusCode(200);
    }
}