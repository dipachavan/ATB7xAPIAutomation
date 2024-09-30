package org.automation.com.ex_22092024.gson.serialization;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GSONSerialization {

    RequestSpecification requestSpecification= RestAssured.given();
    ValidatableResponse validatableResponse;
    Response response;
    String bookingID;

    @Description("Serialization using GSON")
    @Test
    public void createBookingID(){

        Booking booking= new Booking();
        booking.setFirstname("Deepa");
        booking.setLastname("Vilas Chavan");
        booking.setDepositpaid(true);
        booking.setTotalprice(1200);
        bookingdates bookingdates=new bookingdates();
        bookingdates.setCheckin("2024-12-12");
        bookingdates.setCheckout("2024-12-12");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Lunch");

        System.out.println(booking);        //org.automation.com.ex_22092024.gson.serialization.Booking@36dce7ed  //This will print the object

        //we need to convert this String object to JSON Object(byteStream)->Serialization
        Gson gson= new Gson();
        String jsonPayLoad= gson.toJson(booking);
        System.out.println(jsonPayLoad);


        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonPayLoad).log().all();
        response= requestSpecification.when().post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        bookingID=response.jsonPath().getString("bookingid");
        System.out.println(bookingID);
    }
}
