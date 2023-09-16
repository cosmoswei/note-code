package com.wei;

import com.wei.limit.limiter.FlowControl;
import org.springframework.stereotype.Service;

@Service
public class TestService {


    @FlowControl(limit = 100, time = 5, callback = "flowControl")
    public String test() {
        return String.valueOf(System.currentTimeMillis());
    }

    public String flowControl() {
        return "触发流控！flowControl";
    }
}
