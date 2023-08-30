package com.wei.reflect;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {

        long now;
        long sum = 0;
        TestClass t = new TestClass();
        now = System.currentTimeMillis();
        for (int i = 0; i < 500000; ++i) {
            t.setNum(i);
            sum += t.getNum();
        }
        System.out.println("get-set耗时" + (System.currentTimeMillis() - now) + "ms秒，和是" + sum);
        sum = 0;
        now = System.currentTimeMillis();
        for (int i = 0; i < 500000; ++i) {
            Class<?> c = Class.forName("com.wei.reflect.TestClass");
            Class<?>[] argsType = new Class[1];
            argsType[0] = int.class;
            Method m = c.getMethod("setNum", argsType);
            m.invoke(t, i);
            sum += t.getNum();
        }
        System.out.println("标准反射耗时" + (System.currentTimeMillis() - now) + "ms，和是" + sum);
        sum = 0;
        Class<?> c = Class.forName("com.wei.reflect.TestClass");
        Class<?>[] argsType = new Class[1];
        argsType[0] = int.class;
        Method m = c.getMethod("setNum", argsType);
        now = System.currentTimeMillis();
        for (
                int i = 0;
                i < 500000; ++i) {
            m.invoke(t, i);
            sum += t.getNum();
        }
        System.out.println("缓存反射耗时" + (System.currentTimeMillis() - now) + "ms，和是" + sum);
        sum = 0;
        MethodAccess ma = MethodAccess.get(TestClass.class);
        int index = ma.getIndex("setNum");
        now = System.currentTimeMillis();
        for (
                int i = 0;
                i < 500000; ++i) {
            ma.invoke(t, index, i);
            sum += t.getNum();
        }
        System.out.println("reflectasm反射耗时" + (System.currentTimeMillis() - now) + "ms，和是" + sum);
    }
}
