package org.automation.com.ex_21092024.TestNGExamples;

import org.testng.annotations.Test;

public class TestNG006_Grouping {

    @Test(groups = {"Sanity","Smoke"})
    public void testcase051()
    {
        System.out.println("sanity and smoke");
    }
    @Test(groups = {"Smoke"})
    public void testcase052()
    {
        System.out.println("smoke");
    }
    @Test(groups = {"Reg","Smoke"})
    public void testcase053()
    {
        System.out.println("reg and smoke");;
    }
    @Test(groups = {"Reg","Sanity"})
    public void testcase00()
    {
        System.out.println("reg and sanity");
    }
    @Test(groups = {"Dev"})
    public void testcase001()
    {
        System.out.println("dev");
    }
}

//default priority is 0