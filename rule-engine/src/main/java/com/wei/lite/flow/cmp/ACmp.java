package com.wei.lite.flow.cmp;

import com.wei.aviator.User;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent("a")
public class ACmp extends NodeComponent {


    @Override
    public void process() throws Exception {
        //do your business
        Object requestData = this.getRequestData();
        System.out.println("requestData = " + requestData);
        User user = this.getCmpData(User.class);
        System.err.println("user = " + user);
//        OrderContext orderContext = this.getContextBean("OrderContextName");
//        System.err.println("orderContext = " + orderContext.getOrderNo());
        System.err.println("this.getClass().getCanonicalName() = " + this.getClass().getCanonicalName());
    }
}
