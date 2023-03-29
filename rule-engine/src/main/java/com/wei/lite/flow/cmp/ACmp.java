package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.annotation.LiteflowMethod;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.enums.LiteFlowMethodEnum;
import com.yomahub.liteflow.slot.Slot;

@LiteflowComponent("a")
@LiteflowCmpDefine
public class ACmp {

    @LiteflowMethod(LiteFlowMethodEnum.PROCESS)
    public void processAcmp(NodeComponent bindCmp) {
        System.out.println("ACmp executed!");
    }

    @LiteflowMethod(LiteFlowMethodEnum.IS_ACCESS)
    public boolean isAcmpAccess(NodeComponent bindCmp) {
        return true;
    }

    @LiteflowMethod(LiteFlowMethodEnum.BEFORE_PROCESS)
    public void beforeAcmp(String nodeId, Slot slot) {
        System.out.println("before A");
    }

    @LiteflowMethod(LiteFlowMethodEnum.AFTER_PROCESS)
    public void afterAcmp(String nodeId, Slot slot) {
        System.out.println("after A");
    }

    @LiteflowMethod(LiteFlowMethodEnum.ON_SUCCESS)
    public void onAcmpSuccess(NodeComponent bindCmp) {
        System.out.println("Acmp success");
    }

    @LiteflowMethod(LiteFlowMethodEnum.ON_ERROR)
    public void onAcmpError(NodeComponent bindCmp) {
        System.out.println("Acmp error");
    }

    @LiteflowMethod(LiteFlowMethodEnum.IS_END)
    public boolean isAcmpEnd(NodeComponent bindCmp) {
        return false;
    }
}
