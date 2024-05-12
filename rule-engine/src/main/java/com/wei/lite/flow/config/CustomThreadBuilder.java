package com.wei.lite.flow.config;

import com.yomahub.liteflow.thread.ExecutorBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomThreadBuilder implements ExecutorBuilder {
    @Override
    public ExecutorService buildExecutor() {
        return Executors.newCachedThreadPool();
    }
}
