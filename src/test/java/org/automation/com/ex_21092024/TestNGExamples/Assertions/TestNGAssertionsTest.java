package org.automation.com.ex_21092024.TestNGExamples.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertionsTest
{
    @Test
    public void softAssertTest()
    {
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(10,12);
        System.out.println("any how executed");
        //softAssert.assertAll();   //will report all collected errors
    }
    @Test
    public void hardAssertTest()
    {
        Assert.assertTrue(false);
        System.out.println("will not be executed");
    }

    @Test
    public void TC01_Assertions_Test() {
        RequestSpecification requestSpecification = RestAssured.given();
        ValidatableResponse validatableResponse;
        Response response;
        String postPayload = "{\n" +
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
        response = requestSpecification.when().post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Integer bookingID=response.then().extract().path("bookingid");
        String firstName=response.then().extract().path("booking.firstname");
        Assert.assertNotNull(bookingID);
        Assert.assertEquals(firstName,"Auto QA");

    }
}

//any how executed