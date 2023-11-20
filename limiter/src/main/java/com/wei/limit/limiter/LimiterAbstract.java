package com.wei.limit.limiter;

/**
 * 此处是为了让限流实现类必须实现 check(),其他方法按需索取
 */
public abstract class LimiterAbstract implements Limiter {

    @Override
    public void set(String key, Integer value, long time) {
    }

    @Override
    public Integer get(String key) {
        return null;
    }

    @Override
    public void remove(String key) {
    }

    @Override
    public void incr(String key, int time) {
    }
}
