package com.course.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicAnntation {
    @Test
    public void tesCase1(){
        System.out.println("这是测试用例1");
    }
   @Test
    public void testCase2(){
       System.out.println("这是测试用例2");
   }

   @BeforeMethod
    public void beforMethod(){
       System.out.println("beforMethod");
   }
   @AfterMethod
    public void afterMethod(){
       System.out.println("afterMethod");
   }



}
