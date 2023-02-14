package com.wei.util.function;

/**
 * 分支处理接口
 **/
@FunctionalInterface
public interface BooleanHandler {

    /**
     * 分支操作
     *
     * @param handler 要进行的操作
     * @return void
     **/
    void handler(Runnable handler);

}