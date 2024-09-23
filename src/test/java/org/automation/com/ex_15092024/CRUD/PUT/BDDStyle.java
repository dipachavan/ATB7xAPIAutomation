package org.automation.com.ex_15092024.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class BDDStyle {


    @Test
    public void test_put_positive(){
        String token="38a8dfbfff62ebf";
        String bookingID="770";
        String putPayload="{\n" +
                "    \"firstname\" : \"Deeepaa\",\n" +
                "    \"lastname\" : \"Vilas Chavan\",\n" +
                "    \"totalprice\" : 100412,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-12-12\",\n" +
                "        \"checkout\" : \"2024-12-12\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast,Lunch and Dinner\"\n" +
                "}";

        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/"+bookingID)
        .contentType(ContentType.JSON)
        .cookie("token",token)
        .body(putPayload).log().all().when().put().then().log().all().statusCode(200);
    }
}
