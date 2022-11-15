package com.wei;

import com.wei.context.CustomContext;
import com.wei.context.OrderContext;
import com.wei.context.SignContext;
import com.wei.context.UserContext;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.flow.entity.CmpStep;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class YourClass {

    @Resource
    private FlowExecutor flowExecutor;

    public void testConfig() throws ExecutionException, InterruptedException {
        LiteflowResponse liteflowResponse = flowExecutor.execute2Resp("chain1", "arg");
    }
}
