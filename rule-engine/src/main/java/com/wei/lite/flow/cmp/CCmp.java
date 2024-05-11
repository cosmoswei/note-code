package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("c")
public class CCmp extends NodeComponent {

	@Override
	public void process() {
		System.err.println("this.getClass().getCanonicalName() = " + this.getClass().getCanonicalName());
	}
}
