package com.wei.template;//取钱业务的实现类

import org.springframework.stereotype.Component;

public class DrawMoneyHandler extends AbstractBusinessHandler {

    @Override
    public void handle() {

        System.out.println("draw 1000");

    }

}