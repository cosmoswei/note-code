package com.wei.lite.flow.config;

import com.yomahub.liteflow.flow.id.RequestIdGenerator;

public class CustomRequestIdGenerator implements RequestIdGenerator {

    @Override
    public String generate() {
        return String.valueOf(System.nanoTime());
    }
}
