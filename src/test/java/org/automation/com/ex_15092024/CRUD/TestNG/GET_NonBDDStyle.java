package org.automation.com.ex_15092024.CRUD.TestNG;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GET_NonBDDStyle {
    @Test
    @Description("GET NON BDD Style")
    public void getDetails() {
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://api.zippopotam.us/");
        r.basePath("IN/560037").log().all();
        r.when().get();
        r.then().log().all().statusCode(200);

    }
}
