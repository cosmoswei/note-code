package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeForComponent;

@LiteflowComponent("f")
public class FCmp extends NodeForComponent {
    @Override
    public int processFor() throws Exception {
        //这里根据业务去返回for的结果
        return 20;
    }
}
