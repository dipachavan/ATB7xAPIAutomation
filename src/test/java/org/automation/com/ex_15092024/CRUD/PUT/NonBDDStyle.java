package org.automation.com.ex_15092024.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDStyle {


    @Test
    public void test_put_positive(){
        RequestSpecification requestSpecification;
        ValidatableResponse validatableResponse;
        Response response;
        String token="2c8aa5f4166b102";
        String bookingID="733";
        String putPayload="{\n" +
                "    \"firstname\" : \"Fname\",\n" +
                "    \"lastname\" : \"Lname\",\n" +
                "    \"totalprice\" : 10012,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-10\",\n" +
                "        \"checkout\" : \"2023-15-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch and Dinner\"\n" +
                "}";

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(putPayload).log().all();

        response= requestSpecification.when().put();
        validatableResponse=response.then().log().all();
        //validatableResponse.statusCode(200);
    }
}
