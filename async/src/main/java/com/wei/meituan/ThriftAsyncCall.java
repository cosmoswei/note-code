package com.wei.meituan;

@FunctionalInterface
public interface ThriftAsyncCall {
    void invoke() throws RuntimeException;
}