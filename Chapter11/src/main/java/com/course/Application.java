package com.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/*
@EnableScheduling用来开启定时任务，即发现注解@Scheduled的任务并后台执行。
@Scheduled用于标注这个方法是一个定时任务的方法

@SpringBootApplication用来标注项目入口，以及完成一些基本的自动配置。
 */
@EnableScheduling
@SpringBootApplication
public class Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context = SpringApplication.run(Application.class, args);
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
