package org.automation.com;

public class NoDesignPattern
{
    public static void main(String[] args) {
        NoDesignPattern np=new NoDesignPattern();
        np.method1();
        np.method2();
        np.method3();
    }
    public void method1()
    {
        System.out.println("method 1");
    }
    public void method2()
    {
        System.out.println("method 2");
    }
    public void method3()
    {
        System.out.println("method 3");
    }
}
//output
/*
method 1
method 2
method 3
 */