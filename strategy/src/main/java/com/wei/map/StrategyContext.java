package com.wei.map;

import com.wei.map.impl.Food;
import com.wei.map.impl.Hotel;
import com.wei.map.impl.Waimai;

import java.util.HashMap;
import java.util.Map;

public class StrategyContext {

    private StrategyContext() {

    }

    private static final Map<String, Strategy> regeisterMap = new HashMap<>();

    // 注册策略
    public static void registerStrategy(String rewardType, Strategy strategy) {
        regeisterMap.putIfAbsent(rewardType, strategy);
    }

    public static Strategy getStrategyV2(String rewardType) {
        return regeisterMap.get(rewardType);
    }

    public static Strategy getStrategy(String rewardType) {
        switch (rewardType) {
            case "Waimai":
                return new Waimai();
            case "Hotel":
                return new Hotel();
            case "Food":
                return new Food();
            default:
                throw new IllegalArgumentException("unknown rewardType");
        }
    }

}

