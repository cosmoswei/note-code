package com.example.retry;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
@Slf4j
public class EventTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void msgTest() {
        applicationContext.publishEvent(new MsgEvent("123"));
        log.info("短信发送事件发布成功");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}