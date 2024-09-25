package org.automation.com.ex_21092024.TestNGExamples;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG005_Priority {

    @Test(priority = -2)
    public void testcase051(){
        System.out.println("3");
    }
    @Test(priority = -1)
    public void testcase052(){
        System.out.println("5");
    }
    @Test(priority = 5)
    public void testcase053(){
        System.out.println("1");;
    }
    @Test(priority = -1)
    public void testcase00(){
        System.out.println("8");
    }
}

//default priority is 0