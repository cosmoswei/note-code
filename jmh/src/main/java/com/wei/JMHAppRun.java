package com.wei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wei.mapper")
public class JMHAppRun {
    public static void main(String[] args) {
        SpringApplication.run(DataAppRun.class, args);
    }

}
