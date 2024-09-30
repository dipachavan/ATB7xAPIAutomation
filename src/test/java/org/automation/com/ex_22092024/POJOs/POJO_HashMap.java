package org.automation.com.ex_22092024.POJOs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;


public class POJO_HashMap
{
    RequestSpecification requestSpecification= RestAssured.given();
    ValidatableResponse validatableResponse;
    Response response;
    String bookingID;
    @Test
    public void createBookingID_usingHashMap()
    {
        Map<String,Object> hashMapPayload= new LinkedHashMap<>();
        //here key is of type String and for value , Object is taken as data type is varying.
        hashMapPayload.put("firstname","Deepa");
        hashMapPayload.put("lastname","Chavan");
        hashMapPayload.put("totalprice",123456);
        hashMapPayload.put("depositpaid",true);
        Map<String,Object> bookingDatesMap= new LinkedHashMap<>();
        bookingDatesMap.put("checkin","2024-10-10");
        bookingDatesMap.put("checkout","2024-12-12");
        //bookingdates is another key value pair so we are adding this map to original map
        hashMapPayload.put("bookingdates",bookingDatesMap);
        hashMapPayload.put("additionalneeds","Dinner");
        System.out.println(hashMapPayload);

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(hashMapPayload).log().all();
        response= requestSpecification.when().post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        bookingID=response.jsonPath().getString("bookingid");
        System.out.println(bookingID);

    }

}


//Getting this exception because RA does not support MAP.It supports Sting only by default.
// need to add 2 types of libraries(3 party)
//    1)gson
//    2)jackson


/*
Will get below error if above dependencies are not added.

java.lang.IllegalStateException: Cannot serialize object because no JSON serializer found in classpath. Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath.

at java.base/jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(DirectConstructorHandleAccessor.java:62)


 */