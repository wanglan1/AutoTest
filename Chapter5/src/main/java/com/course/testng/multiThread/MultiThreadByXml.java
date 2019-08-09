package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadByXml {
    @Test
    public void test1(){
        System.out.println("test1---Thread Id :  " + Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.println("test2---Thread Id :  " + Thread.currentThread().getId());
    }
    @Test
    public void test3(){
        System.out.println("test3---Thread Id :  " + Thread.currentThread().getId());
    }

}
