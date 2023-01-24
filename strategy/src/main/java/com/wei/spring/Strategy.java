package com.wei.spring;

import java.math.BigDecimal;

/**
 * 计算价格的接口
 *
 * @author Bowin
 */
public interface Strategy {

    String name = "huangxuwei";

    /**
     * 计算价格
     *
     * @return
     */
    BigDecimal calculatePrice();
}