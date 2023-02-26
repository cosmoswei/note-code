package com.wei;

import com.wei.test.CustomTaskDecorator;
import com.wei.test.UserUtils;
import com.wei.test.UserUtilsTran;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
@Slf4j
class ThreadLocalTests {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void test2() {
        UserUtils.setUserId("121");
        log.info("parent info " + UserUtils.getUserId());
        CompletableFuture.runAsync(() -> log.info("kid info " + UserUtils.getUserId()),
                threadPoolTaskExecutor);
    }

    @Test
    public void test3() {
        UserUtilsTran.setUserId("121");
        log.info("parent info " + UserUtilsTran.getUserId());
        CompletableFuture.runAsync(() -> log.info("kid info " + UserUtilsTran.getUserId()));
    }

    @Bean("asyncServiceExecutor")
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
