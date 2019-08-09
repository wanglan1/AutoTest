package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    @Test(dataProvider = "provideInfo")
    public void test1(String name, int age) {
        System.out.println(name + ": " + age);
    }

    @Test(dataProvider = "provideInfo")
    public void test2(int id, int age) {
        System.out.println(id + ": " + age);
    }


    @DataProvider(name = "provideInfo")
    public Object[][] provideData(Method method) {
        if (method.getName().equals("test1")){
            return new Object[][] { { "张三", 30 }, { "李四", 40 }, { "王五", 50 } };
        } else if (method.getName().equals("test2")){
            return new Object[][] { { 1, 30 }, { 2, 40 }, { 3, 50 } };
        }
        return null;
    }
}
