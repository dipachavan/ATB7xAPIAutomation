package org.automation.com;

import io.restassured.RestAssured;

public class Test002
{

    public static void main(String[] args) {
        System.out.println("Hello, welcome to RestAssured");
        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .basePath("booking/1").log().all()
                .when().get().then().log().all()
                .statusCode(200);
    }
}
