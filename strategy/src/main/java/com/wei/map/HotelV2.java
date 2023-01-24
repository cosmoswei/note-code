package com.wei.map;


public class HotelV2 extends AbstractStrategy implements Strategy{

    private static final HotelV2 instance = new HotelV2();

    private HotelV2() {
        register();
    }

    @Override
    public void issue(Object... params) {
        System.out.println("酒店策略！");
    }

    public static HotelV2 getInstance() {
        return instance;
    }
}
