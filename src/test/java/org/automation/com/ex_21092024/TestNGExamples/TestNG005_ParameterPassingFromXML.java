package org.automation.com.ex_21092024.TestNGExamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG005_ParameterPassingFromXML {

    //this value should match with the name property from testng.xml file
    @Parameters("browser")
    @Test()
    public void testcase00(String nameValue)
    {
        System.out.println("Execute me with browser: "+nameValue);
    }


}

//Execute me with browser: Chrome

//Chrome is taken from testng.xml file(testng_examples_ParameterPassing.xml)

//if browser property is not found or mismatch(wrong spelled) in @Test file then below error will be thrown:
/*

org.testng.TestNGException:
Parameter 'browser' is required by @Test on method testcase00 but has not been marked @Optional or defined
in C:\Users\deepa\IdeaProjects\ATB7xAPIAutomation\testng_examples_ParameterPassing_1.xml




 */