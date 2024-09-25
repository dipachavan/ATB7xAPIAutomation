package org.automation.com.ex_21092024.TestNGExamples;

import org.testng.annotations.Test;

public class TestNG005_DependsOn {

    @Test(dependsOnMethods = "TC01")
    public void testcase00(){
        System.out.println("Execute me after TC01");
    }
    @Test
    public void TC01(){
        System.out.println("Execute me first");
    }

}

//default priority is 0