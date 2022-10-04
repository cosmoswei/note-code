package com.wei;

public class GCTest {
    public static void main(String[] args) {
        new GCTest();
        System.gc();
        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("GC Test Gc finalize ");
    }
}
