package com.wei.lite.flow.context;

import com.yomahub.liteflow.context.ContextBean;
import lombok.Data;

@Data
@ContextBean("OrderContextName")
public class OrderContext {

    String orderNo;
}
