package org.automation.com.ex_15092024.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class NonBDDStyleTCs {
    static RequestSpecification r= RestAssured.given();
    public static void main(String[] args)
    {
        r.baseUri("https://api.zippopotam.us/");
        TC01();
        TC02();
    }

    private static void TC01()
    {
        r.basePath("IN/560037").log().all();
        r.when().get();
        r.then().log().all().statusCode(200);
    }
    private static void TC02()
    {
        r.basePath("IN/400001").log().all();
        r.when().get();
        r.then().log().all().statusCode(200);
    }
}
