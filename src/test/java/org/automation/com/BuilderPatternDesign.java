package org.automation.com;

public class BuilderPatternDesign
{
    public static void main(String[] args) {
        BuilderPatternDesign bp=new BuilderPatternDesign();
        bp.method1().method2().method3();

    }

    public BuilderPatternDesign method1()
    {
        System.out.println("method 1");
        return this;
    }
    public BuilderPatternDesign method2()
    {
        System.out.println("method 2");
        return this;
    }
    public BuilderPatternDesign method3()
    {
        System.out.println("method 3");
        return this;
    }
}
//output
/*
method 1
method 2
method 3
 */