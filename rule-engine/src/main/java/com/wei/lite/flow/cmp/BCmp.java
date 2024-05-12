package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("b")
public class BCmp extends NodeComponent {

    @Override
    public void process() throws InterruptedException {

//        CustomContext context = this.getContextBean(CustomContext.class);
//        context.setStr("this is from Node B");
        TimeUnit.SECONDS.sleep(5);
        //或者你也可以用这个方法去获取上下文实例，如果你只有一个上下文，那么和上面是等价的
        //CustomContext context = this.getFirstContextBean();
        System.err.println("this.getClass().getCanonicalName() = " + this.getClass().getCanonicalName());
    }
}
