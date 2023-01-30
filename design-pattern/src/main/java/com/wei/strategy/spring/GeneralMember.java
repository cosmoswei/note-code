package com.wei.strategy.spring;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 普通用户
 *
 * @author Bowin
 */
@Service("generalMember")
public class GeneralMember implements Strategy {

    @Override
    public BigDecimal calculatePrice() {
        return new BigDecimal("100");
    }

}