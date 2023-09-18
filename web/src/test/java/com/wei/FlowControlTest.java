package com.wei;

import com.wei.util.ThreadUtils;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
public class FlowControlTest {

    @Resource
    private TestService testService;

    @Test
    public void test() {
        ConfigurableApplicationContext context = SpringApplication.run(WebRun.class);
        // 获取需要测试的bean
        this.testService = (TestService) context.getBean("testService");
        int cnt = 0;
        while (true) {
            testService.test();
            ThreadUtils.safeSleep(300);
            cnt++;
            System.out.println("LocalDateTime.now() = "
                    + LocalDateTime.now()
                    + "cnt = "
                    + cnt);
        }
    }
}
