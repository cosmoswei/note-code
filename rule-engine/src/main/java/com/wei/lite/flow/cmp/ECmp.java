package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowMethod;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.enums.LiteFlowMethodEnum;
import com.yomahub.liteflow.enums.NodeTypeEnum;
import org.springframework.stereotype.Component;

@Component("e")
@LiteflowCmpDefine(NodeTypeEnum.SWITCH)
public class ECmp {

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS_SWITCH, nodeType = NodeTypeEnum.SWITCH)
    public String processSwitch(NodeComponent bindCmp) throws Exception {
        System.err.println("【NodeTypeEnum.SWITCH】this.getClass().getCanonicalName() = " + this.getClass().getCanonicalName());
        return "s1";
    }
}
