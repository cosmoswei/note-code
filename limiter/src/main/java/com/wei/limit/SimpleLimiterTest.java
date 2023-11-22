package com.wei.limit;

import com.wei.limit.aop.SimpleLimiter;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.util.ThreadUtils;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class SimpleLimiterTest {


    public void prueTest() {
        ThreadUtils.safeSleep(1);
    }


    @SimpleLimiter(limit = 500000, interval = 1000, type = SimpleLimiterConstant.COUNTER, callback = "callback")
    public void counterTest() {
        ThreadUtils.safeSleep(1);
    }


    @SimpleLimiter(limit = 500000, interval = 1000, type = SimpleLimiterConstant.SLIDING_WINDOW_V1, callback = "callback")
    public void slidingWindowV1Test() {
        ThreadUtils.safeSleep(1);
    }

    @SimpleLimiter(limit = 500000, interval = 1000, type = SimpleLimiterConstant.SLIDING_WINDOW_V2, callback = "callback")
    public void slidingWindowV2Test() {
        ThreadUtils.safeSleep(1);
    }

    public void sentinelTest() {
        ThreadUtils.safeSleep(1);
    }

    @RateLimiter(name = "myRateLimiter")
    public void resilience4jTest() {
        ThreadUtils.safeSleep(1);
    }

    public String callback() {
        return "触发流控";
    }

}
