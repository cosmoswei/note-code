package com.wei.limit.limiter.DTO;

/**
 * Author: Xhy
 * CreateTime: 2023-03-09 16:31
 * 用于封装不同限流方案中的参数
 * 如果其他限流方案需要其他参数则在这里更新并在aop中添加到该bean
 */
public class LimiterDTO {


    /**
     * 限制次数
     */
    public final int limit;

    /**
     * 限制时间
     */
    public final int time;

    /**
     * 用于不同具体限流实现
     */
    public final String key;

    public LimiterDTO(int limit, int time, String key){
        this.limit = limit;
        this.time = time;
        this.key = key;
    }
}
