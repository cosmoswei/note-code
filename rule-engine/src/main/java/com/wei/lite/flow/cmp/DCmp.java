package com.wei.lite.flow.cmp;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("d")
public class DCmp extends NodeComponent {

	@Override
	public void process() {
		//do your business
		System.out.println("ECmp");

	}
}
