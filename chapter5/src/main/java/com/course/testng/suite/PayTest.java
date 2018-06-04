package com.course.testng.suite;

import org.testng.annotations.Test;

public class PayTest {

    @Test(timeOut = 3000)
    public void Pay(){
        Thread.sleep(2000);
        System.out.println("支付成功了~");

    }
}
