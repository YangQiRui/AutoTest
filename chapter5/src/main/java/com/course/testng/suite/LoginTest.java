package com.course.testng.suite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

//    @Test(dataProvider = "data")
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void ParamTest(String name,int age){
        System.out.println("name="+name+";age="+age);
        System.out.printf("Thread id: %s%n",Thread.currentThread().getId());
    }
    @DataProvider(name="data")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",20}
        };
        return o;
    }
}
