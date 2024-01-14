package com.wei;

import java.lang.instrument.Instrumentation;

public class MyPreMain {

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Hi, I'm premain agent! 2:" + agentArgs);
        inst.addTransformer(new TimingTransformer(agentArgs));
    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }
}
