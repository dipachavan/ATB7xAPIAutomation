package org.automation.com.ex_21092024.TestNGExamples.CRUD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;

public class e2eRestBookerTest
{
    String token;
    String bookingID;
    RequestSpecification requestSpecification=RestAssured.given();
    ValidatableResponse validatableResponse;
    Response response;

    public String getToken()
    {
        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);
        response= requestSpecification.when().post();
        validatableResponse= response.then();
        validatableResponse.statusCode(200);

        token=response.jsonPath().getString("token");
        System.out.println(token);
        return token;
    }
    public String createBookingID()
    {
        String postPayload="{\n" +
                "    \"firstname\" : \"Auto QA\",\n" +
                "    \"lastname\" : \"Tester\",\n" +
                "    \"totalprice\" : 100000,\n" +
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
        bookingID=response.jsonPath().getString("bookingid");
        System.out.println(bookingID);
        return bookingID;
    }
    @Test
    public void updateBookingID()
    {
        bookingID=createBookingID();
        token=getToken();
        String putPayload="{\n" +
                "    \"firstname\" : \"Automation QA\",\n" +
                "    \"lastname\" : \"Tester\",\n" +
                "    \"totalprice\" : 200000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-11-10\",\n" +
                "        \"checkout\" : \"2023-12-10\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast and Dinner\"\n" +
                "}";

        //requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(putPayload).log().all();
        response= requestSpecification.when().put();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
    }
    @Test
    public void deleteBooking()
    {
        bookingID=createBookingID();
        token=getToken();
        //requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        response= requestSpecification.when().delete();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
        String message = response.body().asString();
        Assert.assertEquals("Created",message);
    }
    @Test(dependsOnMethods = "deleteBooking")
    public void getBookingIDAfterDelete()
    {
        //requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        response= requestSpecification.when().get();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(404);
        String message = response.body().asString();
        Assert.assertEquals("Not Found",message);
    }
}
