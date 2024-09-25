package org.automation.com.ex_21092024.TestNGExamples;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG005 {
    //@Description("Verify true == true")
    @Test
    public void testcase051(){
        Assert.assertEquals(true,true);
    }
    @Description("Verify true ==false")
    @Test
    public void testcase052(){
        Assert.assertEquals(true,true);
    }
    @Description("Verify false == false")
    @Test
    public void testcase053(){
        Assert.assertEquals(false,false);
    }
}
