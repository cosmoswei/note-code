package com.wei.template.v2;

import java.math.BigDecimal;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author Hollis
 */

public class BankBusinessHandler {

    public void save(BigDecimal amount) {

        execute(a -> System.out.println("save " + amount));

    }

    public void draw(BigDecimal amount) {
        execute(a -> System.out.println("draw " + amount));
    }

    public void moneyManage(BigDecimal amount) {
        execute(a -> System.out.println("draw " + amount));
    }

    private void execute(Consumer<BigDecimal> consumer) {

        getNumber();

        consumer.accept(null);

        judge();

    }

    private void getNumber() {

        System.out.println("number-00" + new Random().nextInt());

    }

    private void judge() {

        System.out.println("give a praised");

    }

}