package com.wei.drools;

import com.wei.entity.Rule;
import com.wei.mapper.RuleMapper;
import com.wei.uniform.entity.Activity;
import com.wei.uniform.entity.ActivityResult;
import org.apache.commons.lang.time.DateUtils;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ActivityService {

    @Resource
    private RuleMapper ruleMapper;

    public ActivityResult getActivity(Activity activity) {
        activity.setEndDate(DateUtils.addDays(new Date(), -1));
        ActivityResult activityResult = new ActivityResult();
        activityResult.setSuccess(true);
        activityResult.setMsg(new StringBuilder());
        OrderDiscount orderDiscount = new OrderDiscount();
        KieHelper kieHelper = new KieHelper();
        Rule discountRule = ruleMapper.getByName("activityRule");
        kieHelper.addContent(discountRule.getContent(), ResourceType.DRL);
        KieSession kieSession = kieHelper.build().newKieSession();
        // 设置折扣对象
        kieSession.setGlobal("activityResult", activityResult);
        kieSession.setGlobal("date", new Date());
        // 设置订单对象
        kieSession.insert(activity);
        // 触发规则
        kieSession.fireAllRules();
        // 中止会话
        kieSession.dispose();
        return activityResult;

    }
}
