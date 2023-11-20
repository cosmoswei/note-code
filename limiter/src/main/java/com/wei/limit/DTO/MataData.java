package com.wei.limit.DTO;

import lombok.Data;

/**
 * 用于封装不同限流方案中的参数
 * 如果其他限流方案需要其他参数则在这里更新并在aop中添加到该bean
 */
@Data
public class MataData {

    /**
     * 限制次数
     */
    public final int limit;

    /**
     * 限制时间
     */
    public final int interval;

    /**
     * 用于不同具体限流实现
     */
    public final String key;

    public MataData(int limit, int interval, String key) {
        this.limit = limit;
        this.interval = interval;
        this.key = key;
    }
}
