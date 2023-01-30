package com.wei.template;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 模板方法设计模式的抽象类
 *
 * @author hollis
 */

public abstract class AbstractBusinessHandler {

    /**
     * 模板方法
     */
    protected void execute(Supplier<String> supplier, Consumer<BigDecimal> consumer) {


        getNumber();

        String number = supplier.get();

        System.out.println(number);


        if (number.startsWith("vip")) {

            //Vip号分配到VIP柜台

            System.out.println("Assign To Vip Counter");

        } else if (number.startsWith("reservation")) {

            //预约号分配到专属客户经理

            System.out.println("Assign To Exclusive Customer Manager");

        } else {

            //默认分配到普通柜台

            System.out.println("Assign To Usual Manager");

        }

        consumer.accept(null);

        judge();

    }

    /**
     * 取号
     *
     * @return
     */

    private void getNumber() {

        System.out.println("now: " + LocalDate.now());

    }

    /**
     * 办理业务
     */
    public abstract void handle(); //抽象的办理业务方法，由子类实现

    /**
     * 评价
     */
    private void judge() {

        System.out.println("give a praised");

    }

}