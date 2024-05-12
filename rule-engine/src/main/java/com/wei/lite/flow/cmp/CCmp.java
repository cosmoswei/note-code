package com.wei.lite.flow.cmp;

import com.wei.aviator.User;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("c")
public class CCmp extends NodeComponent {

	@Override
	public void process() {

//		CustomContext context = this.getContextBean(CustomContext.class);
//		String str = context.getStr();
//		System.err.println("str = " + str);
		System.err.println("this.getClass().getCanonicalName() = " + this.getClass().getCanonicalName());
	}
}
