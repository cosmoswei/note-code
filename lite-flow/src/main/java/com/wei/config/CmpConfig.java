package com.wei.config;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.annotation.LiteflowMethod;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.enums.LiteFlowMethodEnum;
import com.yomahub.liteflow.enums.NodeTypeEnum;

@LiteflowComponent
public class CmpConfig {

    //普通组件的定义
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeId = "a")
    public void processA(NodeComponent bindCmp) {
        //...
    }

    //SWITCH组件的定义
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS_SWITCH, nodeId = "b", nodeType = NodeTypeEnum.SWITCH)
    public String processB(NodeComponent bindCmp) {
        //...
        return null;
    }
    
    //IF组件的定义
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS_IF, nodeId = "c", nodeType = NodeTypeEnum.IF)
    public boolean processC(NodeComponent bindCmp) {
        //...
        return false;

    }
    
    //FOR组件的定义
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS_FOR, nodeId = "d", nodeType = NodeTypeEnum.FOR)
    public int processD(NodeComponent bindCmp) {
        //...
        return 0;
    }
    
    //WHILE组件的定义
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS_WHILE, nodeId = "e", nodeType = NodeTypeEnum.WHILE)
    public int processE(NodeComponent bindCmp) {
        //...
        return 0;
    }
    
    //BREAK组件的定义
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS_BREAK, nodeId = "f", nodeType = NodeTypeEnum.BREAK)
    public int processF(NodeComponent bindCmp) {
        //...
        return 0;
    }
}
