package org.automation.com.ex_15092024.CRUD.TestNG;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GET_BDDStyleGET {
    @Test
    @Description("GET BDD Style")
    public void getDetails()
    {
        System.out.println("Hello, welcome to RestAssured");
        RestAssured
                .given()
                    .baseUri("https://api.zippopotam.us/")
                .basePath("IN/560037").log().all()
                .when().get().then().log().all()
                .statusCode(200);

    }
}
