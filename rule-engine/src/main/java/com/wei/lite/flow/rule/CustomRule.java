package com.wei.lite.flow.rule;

import com.yomahub.liteflow.builder.LiteFlowNodeBuilder;
import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.flow.FlowBus;

public class CustomRule {
    public static void main(String[] args) {
        //构建一个普通组件
        LiteFlowNodeBuilder.createCommonNode().setId("a")
                .setName("组件A")
                .setClazz("com.yomahub.liteflow.test.builder.cmp.ACmp")
                .build();

        //构建一个普通条件组件
        LiteFlowNodeBuilder.createSwitchNode().setId("a")
                .setName("组件A")
                .setClazz("com.yomahub.liteflow.test.builder.cmp.ACmp")
                .build();

        //构建一个脚本组件
        LiteFlowNodeBuilder.createScriptNode().setId("a")
                .setName("组件A")
                .setScript("你的脚本")
                .build();

        //构建一个脚本条件组件
        LiteFlowNodeBuilder.createScriptSwitchNode().setId("a")
                .setName("组件A")
                .setScript("你的脚本")
                .build();

        //构建一个脚本组件，从file载入脚本
        LiteFlowNodeBuilder.createScriptNode().setId("a")
                .setName("组件A")
                .setFile("xml-script-file/s1.groovy")
                .build();

        LiteFlowChainELBuilder.createChain().setChainName("chain2").setEL(
                "THEN(a, b, WHEN(c, d))"
        ).build();

        FlowBus.removeChain("你的流程ID");


    }
}
