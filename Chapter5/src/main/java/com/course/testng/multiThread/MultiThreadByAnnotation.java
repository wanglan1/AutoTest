package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadByAnnotation {

    //threadPoolSize为线程池内可以使用的线程数
    //使用threadPoolSize个线程，将test方法执行invocationCount次
    //timeOut配置的是每次执行该测试方法所耗费时间的阈值，超过阈值则测试失败
    @Test(invocationCount = 10, threadPoolSize = 3,timeOut = 1000)
    public void test(){
        System.out.println("hello");
        System.out.println("Thread Id: " + Thread.currentThread().getId());
    }
}
