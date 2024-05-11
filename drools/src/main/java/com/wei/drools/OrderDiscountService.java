package com.wei.drools;

import com.wei.entity.Rule;
import com.wei.mapper.RuleMapper;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderDiscountService {

    @Resource
    private KieContainer kieContainer;
    @Resource
    private RuleMapper ruleMapper;

    public OrderDiscount getDiscount(OrderRequest orderRequest) {
        OrderDiscount orderDiscount = new OrderDiscount();
        // 开启会话
        KieSession kieSession = kieContainer.newKieSession();
        // 设置折扣对象
        kieSession.setGlobal("orderDiscount", orderDiscount);
        // 设置订单对象
        kieSession.insert(orderRequest);
        // 触发规则
        kieSession.fireAllRules();
        // 中止会话
        kieSession.dispose();
        return orderDiscount;
    }


    public OrderDiscount getDiscount2(OrderRequest orderRequest) {
        OrderDiscount orderDiscount = new OrderDiscount();
        KieHelper kieHelper = new KieHelper();
        Rule discountRule = ruleMapper.getByName("discountRule");
        kieHelper.addContent(discountRule.getContent(), ResourceType.DRL);
        KieSession kieSession = kieHelper.build().newKieSession();
        kieSession.setGlobal("orderDiscount", orderDiscount);
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderDiscount;

    }
}
