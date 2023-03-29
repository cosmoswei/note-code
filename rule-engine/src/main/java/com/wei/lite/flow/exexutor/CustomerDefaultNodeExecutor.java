package com.wei.lite.flow.exexutor;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.flow.executor.NodeExecutor;

public class CustomerDefaultNodeExecutor extends NodeExecutor {
    @Override
    public void execute(NodeComponent instance) throws Exception {
        LOG.info("使用customerDefaultNodeExecutor进行执行");
        super.execute(instance);
      
      	//在这里你可以加入自己的代码，包括上面的代码都可以去掉
      	//但是要确保至少要调用instance.execute()，否组件就真的无法被正确执行了
    }
}
