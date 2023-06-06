package com.wei;

import com.wei.test.UserUtils;
import com.wei.test.UserUtilsTran;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
@Slf4j
class ThreadLocalTests {

    @Qualifier("asyncServiceExecutor")
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    void test2() {
        UserUtils.setUserId("121");
        log.info("parent info " + UserUtils.getUserId());
        System.out.println("threadPoolTaskExecutor.getMaxPoolSize() = " + threadPoolTaskExecutor.getMaxPoolSize());
        CompletableFuture.runAsync(() -> log.info("kid info " + UserUtils.getUserId()), threadPoolTaskExecutor);
    }

    @Test
    void test3() {
        UserUtilsTran.setUserId("121");
        log.info("parent info " + UserUtilsTran.getUserId());
        CompletableFuture.runAsync(() -> log.info("kid info " + UserUtilsTran.getUserId()));
    }


}
