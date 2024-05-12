package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.core.NodeBooleanComponent;
import org.springframework.stereotype.Component;

@Component("x")
public class XCmp extends NodeBooleanComponent {
	@Override
	public boolean processBoolean() throws Exception {
	    //do your biz
		return true;
	}
}
