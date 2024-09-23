package org.automation.com.ex_15092024.CRUD.TestNG;

import org.testng.annotations.*;

public class TC002 {
    @BeforeSuite
    public void demo1()
    {
        System.out.println("BeforeSuite");
    }
    @BeforeClass
    public void demo2()
    {
        System.out.println("BeforeClass");
    }
    @BeforeTest
    public void demo3()
    {
        System.out.println("BeforeTest");
    }
    @BeforeMethod
    public void demo4()
    {
        System.out.println("BeforeMethod");
    }
    @AfterMethod
    public void demo5()
    {
        System.out.println("AfterMethod");
    }
    @AfterTest
    public void demo6()
    {
        System.out.println("AfterTest");
    }
    @AfterClass
    public void demo7()
    {
        System.out.println("AfterClass");
    }
    @AfterSuite
    public void demo8()
    {
        System.out.println("AfterSuite");
    }
    @Test
    public void demo9()
    {
        System.out.println("Method");
    }
}
