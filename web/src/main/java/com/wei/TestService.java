package com.wei;

import com.wei.limit.aop.SimpleLimiter;
import com.wei.limit.constant.SimpleLimiterConstant;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("testService")
public class TestService {

    @SimpleLimiter(limit = 1, interval = 1, type = SimpleLimiterConstant.TOKEN_BUCKET, callback = "flowControl")
    public String test() {
        return LocalDateTime.now().toString();
    }


    @SimpleLimiter(limit = 1, interval = 1, type = SimpleLimiterConstant.TOKEN_BUCKET, callback = "flowControl")
    public String test2() {
        return LocalDateTime.now().toString();
    }

    public String flowControl() {
        return "降级！flowControl";
    }
}
