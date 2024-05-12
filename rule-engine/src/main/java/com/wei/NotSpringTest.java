package com.wei;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.core.FlowExecutorHolder;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.property.LiteflowConfig;

public class NotSpringTest {

    public static void main(String[] args) {
        LiteflowConfig config = new LiteflowConfig();
        config.setRuleSource("config/flowDemo.el.xml");
        FlowExecutor flowExecutor = FlowExecutorHolder.loadInstance(config);
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
    }
}
