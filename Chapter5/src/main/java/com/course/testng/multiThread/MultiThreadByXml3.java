package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadByXml3 {
    @Test
    public void test31(){
        System.out.println("test31---Thread Id :  " + Thread.currentThread().getId());
    }

    @Test
    public void test32(){
        System.out.println("test32---Thread Id :  " + Thread.currentThread().getId());
    }
    @Test
    public void test33(){
        System.out.println("test33---Thread Id :  " + Thread.currentThread().getId());
    }

}
