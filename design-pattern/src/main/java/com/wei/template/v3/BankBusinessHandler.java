package com.wei.template.v3;

import com.wei.template.AbstractBusinessHandler;

import java.math.BigDecimal;
import java.util.Random;

public class BankBusinessHandler extends AbstractBusinessHandler {

    public void saveVip(BigDecimal amount) {

        execute(() -> "vipNumber-00" + new Random().nextInt(), a -> System.out.println("save " + amount));

    }

    public void save(BigDecimal amount) {

        execute(() -> "number-00" + new Random().nextInt(), a -> System.out.println("save " + amount));

    }

    public void saveReservation(BigDecimal amount) {

        execute(() -> "reservationNumber-00" + new Random().nextInt(), a -> System.out.println("save " + amount));

    }

    @Override
    public void handle() {

    }
}