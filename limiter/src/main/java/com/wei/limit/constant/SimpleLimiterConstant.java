package com.wei.limit.constant;


/**
 * 流控常量
 */
public interface SimpleLimiterConstant {
    /**
     * 计数器限流
     */
    String COUNTER = "COUNTER";

    /**
     * 令牌桶限流
     */
    String TOKEN_BUCKET = "TOKEN_BUCKET";

    /**
     * 漏桶限流
     */
    String LEAKY_BUCKET = "LEAKY_BUCKET";

    /**
     * 滑动窗口限流
     */
    String SLIDING_WINDOW = "SLIDING_WINDOW";
    String SLIDING_WINDOW_V2 = "SLIDING_WINDOW_V2";
}
