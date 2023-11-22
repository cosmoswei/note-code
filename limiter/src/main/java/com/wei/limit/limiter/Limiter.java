package com.wei.limit.limiter;


import com.wei.limit.DTO.LimiterMataData;

/**
 * 限流接口
 */
public interface Limiter {

    /**
     * 添加
     *
     * @param key
     * @param value
     * @param interval 秒
     */
    void set(String key, Integer value, long interval);

    /**
     * 获取,可能为空
     *
     * @param key
     * @return
     */
    Integer get(String key);

    /**
     * 删除
     *
     * @param key
     */
    void remove(String key);

    /**
     * 自增
     *
     * @param key
     */
    void incr(String key, int interval);

    /**
     * 检查是否达到限制,子类必须强制实现该接口
     *
     * @param limiterMataData
     * @return
     */
    boolean limit(LimiterMataData limiterMataData);

}
