package com.wei.limit.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SimpleLimiterCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 在这里编写条件判断逻辑
        // 返回 true 表示创建滑动窗口限流器 Bean，返回 false 表示不创建
        // your condition here;
        return false;
    }
}
