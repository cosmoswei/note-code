package com.wei.reflect;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TestClass {
    private long sum;

    public long getNum() {
        return sum;
    }

    public void setNum(int i) {
        this.sum = i;
    }
}
