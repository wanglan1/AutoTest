package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadByXml2 {
    @Test
    public void test21(){
        System.out.println("test21---Thread Id :  " + Thread.currentThread().getId());
    }

    @Test
    public void test22(){
        System.out.println("test22---Thread Id :  " + Thread.currentThread().getId());
    }
    @Test
    public void test23(){
        System.out.println("test23---Thread Id :  " + Thread.currentThread().getId());
    }

}
