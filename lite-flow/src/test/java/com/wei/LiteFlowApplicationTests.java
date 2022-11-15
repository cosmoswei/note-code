package com.wei;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class LiteFlowApplicationTests {

    @Resource
    YourClass yourClass;

    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
        yourClass.testConfig();
    }

}
