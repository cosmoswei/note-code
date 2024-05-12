package com.wei.lite.flow.cmp;

import cn.hutool.core.collection.ListUtil;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeIteratorComponent;

import java.util.Iterator;
import java.util.List;

@LiteflowComponent("i")
public class ICmp extends NodeIteratorComponent {
    @Override
    public Iterator<?> processIterator() throws Exception {
        List<String> list = ListUtil.toList("jack", "mary", "tom");
        return list.iterator();
    }
}
