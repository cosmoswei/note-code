package com.example.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class MsgListener {

    /**
     * value值表示当哪些异常的时候触发重试，
     * maxAttempts表示最大重试次数默认为3，
     * delay表示重试的延迟时间，
     * multiplier表示上一次延时时间是这一次的倍数。
     * @param event
     */
    @EventListener
    @Async
    @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public void sendMsg(MsgEvent event) {
        String msgId = event.getMsgId();
        StopWatch watch = new StopWatch(msgId);
        watch.start();
        log.info("开始发短信");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        watch.stop();
        log.info("短信发送成功, 消息id：【{}】 | 耗时: ({})", msgId, watch.getLastTaskTimeMillis());
    }
}