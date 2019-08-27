package com.tester.extend.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestMethodsDemo {

    @Test
    public void test1(){
        Assert.assertEquals(1, 2);
    }

    @Test
    public void test2(){
        Assert.assertEquals(2,2);
    }

    @Test
    public void test3(){
        Assert.assertEquals("bbb", "bbb");
    }

    @Test
    public void logDemo(){
        Reporter.log("这是我们自己写的日志");
        throw new RuntimeException("运行时异常");
    }
}
