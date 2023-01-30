package com.wei.strategy.spring;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 超级会员
 *
 * @author Bowin
 */
@Service("superMember")
public class SuperMember implements Strategy {

    @Override
    public BigDecimal calculatePrice() {
        return new BigDecimal("1");
    }

}