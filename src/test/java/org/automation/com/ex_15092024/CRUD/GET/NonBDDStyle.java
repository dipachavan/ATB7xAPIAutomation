package org.automation.com.ex_15092024.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class NonBDDStyle {
    public static void main(String[] args) {
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://api.zippopotam.us/");
        r.basePath("IN/560037").log().all();
        r.when().get();
        r.then().log().all().statusCode(200);

    }
}
