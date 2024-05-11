package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent("a")
public class ACmp extends NodeComponent {


    @Override
    public void process() throws Exception {
        System.err.println("this.getClass().getCanonicalName() = " + this.getClass().getCanonicalName());
    }
}
