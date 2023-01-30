package com.wei.strategy.map;


public class FoodV2 extends AbstractStrategy implements Strategy{

    private static final FoodV2 instance = new FoodV2();

    private FoodV2() {
        register();
    }

    @Override
    public void issue(Object... params) {
        System.out.println("酒店策略！");
    }

    public static FoodV2 getInstance() {
        return instance;
    }
}
