package org.automation.com.ex_21092024.TestNGExamples.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class HamcrestAssertionsTest {


    @Test
    public void TC01_Assertions_Test()
    {
        RequestSpecification requestSpecification= RestAssured.given();
        ValidatableResponse validatableResponse;
        Response response;
        String postPayload="{\n" +
                "    \"firstname\" : \"Auto QA\",\n" +
                "    \"lastname\" : \"Tester\",\n" +
                "    \"totalprice\" : 1000,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-10\",\n" +
                "        \"checkout\" : \"2023-12-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch and Dinner\"\n" +
                "}";

        //requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(postPayload).log().all();
        response= requestSpecification.when().post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        //Validation using Hamcrest(RestAssured default library)
        validatableResponse.body("booking.firstname", Matchers.equalTo("Auto QA"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("Tester"));
        validatableResponse.body("bookingid", Matchers.notNullValue());

    }
}
