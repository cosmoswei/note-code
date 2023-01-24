package com.wei.spring;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
/**
 * vip用户
 * @author Bowin
 *
 */
@Service("vipMember")
public class VipMember  implements Strategy{
 
	@Override
	public BigDecimal calculatePrice() {
		return new BigDecimal("80");
	}
 
}