package org.automation.com.ex_15092024.CRUD.GET;

import io.restassured.RestAssured;

public class BDDStyleGET {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to RestAssured");
        RestAssured
                .given()
                    .baseUri("https://api.zippopotam.us/")
                .basePath("IN/560037").log().all()
                .when().get().then().log().all()
                .statusCode(200);

    }
}
