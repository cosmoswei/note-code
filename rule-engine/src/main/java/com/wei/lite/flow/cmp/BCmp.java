package com.wei.lite.flow.cmp;

import com.wei.exexutor.CustomerDefaultNodeExecutor;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.flow.executor.NodeExecutor;
import org.springframework.stereotype.Component;

@Component("b")
public class BCmp extends NodeComponent {

    @Override
    public void process() {
        //do your business
        System.out.println("CCmp");
        String tag = this.getTag();

        System.out.println("tag" + tag);
//        OrderContext orderContext = this.getContextBean(OrderContext.class);
//        UserContext userContext = this.getContextBean(UserContext.class);
//        SignContext signContext = this.getContextBean(SignContext.class);

    }
    @Override
    public Class<? extends NodeExecutor> getNodeExecutorClass() {
        return CustomerDefaultNodeExecutor.class;
    }
}
