package com.wei.pojo;

import lombok.Data;

//代表数据的类
@Data
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }
}