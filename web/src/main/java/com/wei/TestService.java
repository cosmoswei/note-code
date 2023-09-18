package com.wei;

import com.wei.limit.aop.FlowControl;
import com.wei.limit.constant.FlowControlConstant;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("testService")
public class TestService {

    @FlowControl(limit = 1, time = 1, type = FlowControlConstant.TOKEN_BUCKET, callback = "flowControl")
    public String test() {
        return LocalDateTime.now().toString();
    }


    @FlowControl(limit = 1, time = 1, type = FlowControlConstant.TOKEN_BUCKET, callback = "flowControl")
    public String test2() {
        return LocalDateTime.now().toString();
    }

    public String flowControl() {
        return "降级！flowControl";
    }
}
