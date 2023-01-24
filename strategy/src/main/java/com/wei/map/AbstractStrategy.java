package com.wei.map;

abstract class AbstractStrategy implements Strategy {
    public void register() {
        StrategyContext.registerStrategy(getClass().getSimpleName(), this);
    }
}