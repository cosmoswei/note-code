package com.wei.config;

import org.apache.ibatis.session.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * @author PC
 */
//@org.springframework.context.annotation.Configuration
//@Profile({"dev", "qa"})
public class MybatisConfig {

    @Bean
    public MybatisShowSqlInterceptor myInterceptor() {
        return new MybatisShowSqlInterceptor();
    }

    @Bean
    public Configuration configuration(MybatisShowSqlInterceptor myInterceptor) {
        Configuration configuration = new Configuration();
        configuration.addInterceptor(myInterceptor);
        return configuration;
    }
}