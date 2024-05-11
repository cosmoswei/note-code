package com.wei.lite.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wei.lite.flow.cmp"})
public class LiteFlowAppRun {
    public static void main(String[] args) {
        SpringApplication.run(LiteFlowAppRun.class, args);
    }
}
