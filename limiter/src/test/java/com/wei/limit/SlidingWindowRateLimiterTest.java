package com.wei.limit;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SlidingWindowRateLimiterTest {

    @Test
    public void testSlidingWindowRateLimiter() {
        // 创建一个滑动窗口限流器，限制为每秒5次
        SlidingWindowRateLimiterV2 rateLimiter = new SlidingWindowRateLimiterV2(5, 1000);

        // 测试在限流阈值内的请求是否能够正常通过
        for (int i = 0; i < 6; i++) {
            System.out.println(rateLimiter.isRateLimited());
            assertFalse(rateLimiter.isRateLimited());
            rateLimiter.update();
        }

        // 测试超出限流阈值的请求是否被正确限流
        assertTrue(rateLimiter.isRateLimited());

        // 等待限流时间窗口过去
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 测试在限流时间窗口过去后，请求是否能够再次通过
        for (int i = 0; i < 5; i++) {
            assertFalse(rateLimiter.isRateLimited());
            rateLimiter.update();
        }
    }
}
