package com.hand.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zhongLingYun on 2018/8/14.
 * <p>
 * 程序启动入口
 */
@SpringBootApplication
@MapperScan("com.hand.excel.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("启动完毕");
    }
}
