package com.wei.test;

import org.springframework.core.task.TaskDecorator;

public class CustomTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        String userId = UserUtils.getUserId();
        System.out.println("in CustomTaskDecorator: " + userId);
        return () -> {
            try {
                UserUtils.setUserId(userId);
                runnable.run();
            } finally {
                UserUtils.clear();
            }
        };
    }
}
