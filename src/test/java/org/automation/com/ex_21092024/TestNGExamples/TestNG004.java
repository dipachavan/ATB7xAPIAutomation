package org.automation.com.ex_21092024.TestNGExamples;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG004 {
    //@Description("Verify true == true")
    @Test
    public void testcase001(){
        Assert.assertEquals(true,true);
    }
    @Description("Verify true ==false")
    @Test
    public void testcase002(){
        Assert.assertEquals(true,true);
    }
    @Description("Verify false == false")
    @Test
    public void testcase003(){
        Assert.assertEquals(false,false);
    }
}
