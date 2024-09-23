package org.automation.com.ex_15092024.CRUD.TestNG;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC001 {

    @Test
    public void getMethod(){
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us/")
                .basePath("IN/560037").log().all()
                .when().get().then().log().all()
                .statusCode(200);
    }
}
