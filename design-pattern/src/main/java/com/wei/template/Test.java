package com.wei.template;

import com.wei.template.v2.BankBusinessHandler;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        SaveMoneyHandler saveMoneyHandler = new SaveMoneyHandler();
//        saveMoneyHandler.execute();

        System.out.println("==分割1==");

        BankBusinessHandler businessHandler = new BankBusinessHandler();
        businessHandler.save(new BigDecimal("1234"));
        businessHandler.draw(new BigDecimal("1234"));
        businessHandler.moneyManage(new BigDecimal("1234"));

        System.out.println("==分割2==");

        com.wei.template.v3.BankBusinessHandler businessHandlerV3 = new com.wei.template.v3.BankBusinessHandler();

        businessHandlerV3.saveReservation(new BigDecimal("3333"));
    }
}
