package com.wei;

import com.wei.util.mock.MockClass;

public class Main {
    public static void main(String[] args) {
        GlobalExecutor.submitCommonExecutor(() -> {
            System.out.println("huangxweui");
        });
    }
}
