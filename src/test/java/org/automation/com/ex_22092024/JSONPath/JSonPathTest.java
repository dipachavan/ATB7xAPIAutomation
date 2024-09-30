package org.automation.com.ex_22092024.JSONPath;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class JSonPathTest
{
    RequestSpecification requestSpecification= RestAssured.given();
    ValidatableResponse validatableResponse;
    Response response;

    @Description("Extracting Through JSONPath")
    @Test
    public void createBookingID()
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
        System.out.println(response.asString());
        JsonPath jsonPath= new JsonPath(response.asString());
        String bookingID=jsonPath.getString("bookingid");
        String firstName=jsonPath.getString("booking.firstname");
        String lastName=jsonPath.getString("booking.lastname");
        System.out.println(bookingID);
        System.out.println(firstName);
        System.out.println(lastName);

        assertThat(bookingID).isNotBlank().isNotNull();
        assertThat(firstName).isNotBlank().isNotNull().isNotEmpty().isEqualTo("Auto QA");
        assertThat(lastName).isNotBlank().isNotNull().isNotEmpty().isEqualTo("Tester");


    }
}
