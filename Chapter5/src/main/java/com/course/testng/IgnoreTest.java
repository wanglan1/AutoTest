package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest {


    @Test
    public void case1(){
        System.out.println("case1 执行");
    }

    @Test(enabled = false)
    public void case2(){
        System.out.println("case2 执行");
    }

    @Test(enabled = true)
    public void case3(){
        System.out.println("case3 执行");
    }
}
