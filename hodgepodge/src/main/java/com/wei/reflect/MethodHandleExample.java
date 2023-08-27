package com.wei.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleExample {
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh = lookup.findStatic(Math.class, "sqrt", MethodType.methodType(double.class, double.class));

        for (int i = 0; i < 1000000000; i++) {
            double o = (double)mh.invokeExact(2.0);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - start) + "ms");
    }
}