package com.wei.service;

import me.xuqu.palmx.spring.EnablePalmx;
import me.xuqu.palmx.spring.PalmxBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnablePalmx
@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    public PalmxBeanPostProcessor palmxBeanPostProcessor() {
        return new PalmxBeanPostProcessor();
    }
}