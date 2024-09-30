package org.automation.com.ex_22092024.CRUD_E2E;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RestBooker_E2E
{
    RequestSpecification requestSpecification= RestAssured.given();
    ValidatableResponse validatableResponse;
    Response response;
    static Integer bookingID;
    Gson gson= new Gson();
    static String strToken;
    Booking bookingReq= new Booking();
    BookingResponse bookingResponse=new BookingResponse();
    bookingdates bookingdates=new bookingdates();
    PatchResponse patchResponse= new PatchResponse();
    @Description(value = "Creating token")
    @Test(priority = 1)
    public void createToken()
    {
        Token token=new Token();
        token.setUsername("admin");
        token.setPassword("password123");

        String jsonTokenPayLoad = gson.toJson(token);
        System.out.println(jsonTokenPayLoad);

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonTokenPayLoad).log().all();
        response= requestSpecification.when().post();
        String strResponse=response.asString();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        //Deserialization
        TokenResponse tokenResponse= gson.fromJson(strResponse, TokenResponse.class);
        System.out.println(tokenResponse.getTokenResponse());
        strToken=tokenResponse.getTokenResponse();
        //System.out.println("******"+strToken);
        assertThat(tokenResponse.getTokenResponse()).isNotBlank();
        //return tokenResponse;
    }

    @Description("Creation of a Booking ID")
    @Test(priority = 2)
    public void createBookingID()
    {
//        Booking booking= new Booking();
        bookingReq.setFirstname("Deepa");
        bookingReq.setLastname("Vilas Chavan");
        bookingReq.setDepositpaid(true);
        bookingReq.setTotalprice(1200);
//        bookingdates bookingdates=new bookingdates();
        bookingdates.setCheckin("2024-12-12");
        bookingdates.setCheckout("2024-12-12");
        bookingReq.setBookingDates(bookingdates);
        bookingReq.setAdditionalneeds("Lunch");

        System.out.println(bookingReq);        //org.automation.com.ex_22092024.gson.serialization.Booking@36dce7ed  //This will print the object
       //we need to convert this String object to JSON Object(byteStream)->Serialization
//        Gson gson= new Gson();
        String jsonPayLoad= gson.toJson(bookingReq);
        System.out.println(jsonPayLoad);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonPayLoad).log().all();
        response= requestSpecification.when().post();
        validatableResponse=response.then().log().all();
        String strResponse=response.asString();
        //validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
//        bookingID=response.jsonPath().getString("bookingid");
//        System.out.println(bookingID);
        bookingResponse= gson.fromJson(strResponse, BookingResponse.class);
        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBooking().getLastname());
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Deepa").isNotBlank();
        bookingID = bookingResponse.getBookingid();
        //return bookingID;
    }
    @Description("Update  of a Booking ID")
    @Test(priority = 3)
    public void partialUpdateBookingID()
    {
        bookingReq.setFirstname("DeepaChavan");
        bookingReq.setLastname("QA Automation");
        String jsonPartialUpdatePayLoad= gson.toJson(bookingReq);
        System.out.println(jsonPartialUpdatePayLoad);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",strToken);
        requestSpecification.body(jsonPartialUpdatePayLoad).log().all();
        response= requestSpecification.when().patch();
        validatableResponse=response.then().log().all();
        String strResponse=response.asString();
        System.out.println("Patch Resp"+strResponse);
        //validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        //Deserialization
        patchResponse= gson.fromJson(strResponse, PatchResponse.class);
        System.out.println(patchResponse.getFirstname());
        System.out.println(patchResponse.getLastname());
        assertThat(patchResponse.getFirstname()).isEqualTo("DeepaChavan").isNotBlank();
    }
    @Description("Deleting Booking ID")
    @Test(priority = 4)
    public void deleteBookingID()
    {
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        //requestSpecification.cookie("token",strToken);
        response= requestSpecification.when().delete();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);
        String message = response.body().asString();
        Assert.assertEquals("Created",message);
    }
    @Description("Getting Booking ID after its been deleted")
    @Test(priority = 6)
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
    @Description("Creation of a Booking ID with invalid data")
    @Test(priority = 5)
    public void createBookingIDWrongPayload()
    {
//        Booking booking= new Booking();
        String payload_invalid="{\n" +
                "    \"oofirstname\" : \"DipaQAa\",\n" +
                "    \"lastname\" : \"Tester\",\n" +
                "    \"totalprice\" : 34258,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-11-11\",\n" +
                "        \"checkout\" : \"2024-12-12\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"breakfast,Lunch and dinner\"\n" +
                "}";

        System.out.println(payload_invalid);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        //requestSpecification.cookie("token",strToken);
        requestSpecification.body(payload_invalid).log().all();
        response= requestSpecification.when().put();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(400);
        String message = response.body().asString();
        Assert.assertEquals("Bad Request",message);
    }
    @Description("Update of a deleted Booking ID")
    @Test(priority = 7)
    public void updateBookingID()
    {
        bookingReq.setFirstname("DeepaChavan");
        bookingReq.setLastname("QA Automation");
        String jsonUpdatePayLoad= gson.toJson(bookingReq);
        System.out.println(jsonUpdatePayLoad);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        // requestSpecification.cookie("token",strToken);
        requestSpecification.body(jsonUpdatePayLoad).log().all();
        response= requestSpecification.when().put();
        validatableResponse=response.then().log().all();
        String strResponse=response.asString();
        System.out.println("Patch Resp"+strResponse);
        //validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
    }
}
