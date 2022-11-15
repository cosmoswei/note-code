package com.wei.cmp;

import com.yomahub.liteflow.core.NodeIfComponent;
import org.springframework.stereotype.Component;

@Component("x")
public class XCmp extends NodeIfComponent {
	@Override
	public boolean processIf() throws Exception {
	    //do your biz
		return true;
	}
}