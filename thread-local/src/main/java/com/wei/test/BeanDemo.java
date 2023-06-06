package com.wei.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class BeanDemo {

    @Bean
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        log.info("start executor");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);

        executor.setMaxPoolSize(20);

        executor.setQueueCapacity(200);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.setTaskDecorator(new CustomTaskDecorator());

        executor.initialize();
        log.info("exit executor");
        return executor;
    }
}
