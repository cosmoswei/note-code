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
        Future<LiteflowResponse> liteFlowResponseFuture = flowExecutor.execute2Future("chain1", "arg");
        flowExecutor.execute2Future("chain1", "arg");
        LiteflowResponse response2 = flowExecutor.execute2Resp("chain1", "流程初始参数", OrderContext.class, UserContext.class, SignContext.class);
        LiteflowResponse response3 = flowExecutor.execute2Resp("chain1", "流程初始参数", CustomContext.class);
        LiteflowResponse liteflowResponse = liteFlowResponseFuture.get();

        Queue<CmpStep> executeStepQueue = response3.getExecuteStepQueue();
        Map<String, CmpStep> executeSteps = response3.getExecuteSteps();
        CustomContext contextBean = response3.getContextBean(CustomContext.class);
        String executeStepStr = response2.getExecuteStepStr();

        System.out.println(executeStepStr);
        System.out.println(executeSteps.keySet());
        System.out.println(contextBean);
        System.out.println(liteflowResponse);
        System.out.println(executeStepQueue);

    }
}
