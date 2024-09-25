package org.automation.com.ex_21092024.TestNGExamples;

import org.testng.annotations.Test;

public class TestNG005_AlwaysRun {

    @Test(alwaysRun = true)
    public void testcase00()
    {
        System.out.println("Execute me after TC01");
    }
    @Test(enabled = false)
    public void TC01()
    {
        System.out.println("Execute me first");
    }
    @Test(enabled = true)
    public void TC02()
    {
        System.out.println("hello");
    }

}

//alwaysRun = true ,this will anyhow run that TC
//enabled = false, this will skip that TC
//enabled = true, this will not skip that TC