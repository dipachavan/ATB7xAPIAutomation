package org.automation.com.ex_22092024.JSONPath;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class ResponseValidation {
    @Test
    public void responseValidation(){
        String response="{\n" +
                "  \"firstName\": \"Deepa\",\n" +
                "  \"lastName\": \"Chavan\",\n" +
                "  \"age\": 26,\n" +
                "  \"address\": {\n" +
                "    \"streetAddress\": \"naist street\",\n" +
                "    \"city\": \"Nara\",\n" +
                "    \"postalCode\": \"630-0192\"\n" +
                "  },\n" +
                "  \"phoneNumbers\": [\n" +
                "    {\n" +
                "      \"type\": \"iPhone\",\n" +
                "      \"number\": \"0123-4567-8888\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"home\",\n" +
                "      \"number\": \"0123-4567-8910\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        JsonPath jsonPath=JsonPath.from(response);
        System.out.println(jsonPath.getString("firstName"));
        System.out.println(jsonPath.getString("phoneNumbers[1].type"));
        System.out.println(jsonPath.getString("phoneNumbers[0].number"));
        System.out.println(jsonPath.getString("address.streetAddress"));
        System.out.println(jsonPath.getString("address.postalCode"));
        System.out.println(jsonPath.getString("age"));

        //TestNG Assertions
        Assert.assertEquals(jsonPath.getString("firstName"),"Deepa");
        Assert.assertEquals(jsonPath.getString("lastName"),"Chavan");
        assertThat(jsonPath.getString("firstName")).isEqualTo("Deepa").isNotBlank();
    }
}
