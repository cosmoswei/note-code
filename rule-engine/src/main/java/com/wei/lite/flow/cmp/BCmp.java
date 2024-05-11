package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("b")
public class BCmp extends NodeComponent {

    @Override
    public void process() {
        //do your business
        System.err.println("this.getClass().getCanonicalName() = " + this.getClass().getCanonicalName());
    }
}
