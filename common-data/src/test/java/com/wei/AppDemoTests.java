package com.wei;

import com.wei.entity.Rule;
import com.wei.mapper.RuleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest(classes = DataAppRun.class)
@Slf4j
class AppDemoTests {

    @Resource
    private RuleMapper ruleMapper;

    @Test
    void saveRule() {
        long start = System.currentTimeMillis();
        Rule rule = new Rule();
        SeataIdWorker seataIdWorker = new SeataIdWorker((long) WorkIdGenerator.getHostWorkId());
        rule.setId(String.valueOf(seataIdWorker.nextId()));
        rule.setName("discountRule");
        rule.setContent("import com.wei.drools.OrderRequest;\n" +
                "import com.wei.drools.CustomerType;\n" +
                "global com.wei.drools.OrderDiscount orderDiscount;\n" +
                "\n" +
                "dialect \"mvel\"\n" +
                "\n" +
                "// 规则1: 根据年龄判断\n" +
                "rule \"Age based discount\"\n" +
                "    when\n" +
                "        // 当客户年龄在20岁以下或者50岁以上\n" +
                "        OrderRequest(age < 20 || age > 50)\n" +
                "    then\n" +
                "        // 则添加10%的折扣\n" +
                "        System.out.println(\"==========Adding 10% discount for Kids/ senior customer=============\");\n" +
                "        orderDiscount.setDiscount(orderDiscount.getDiscount() + 10);\n" +
                "end\n" +
                "\n" +
                "// 规则2： 根据客户类型的规则\n" +
                "rule \"Customer type based discount - Loyal customer\"\n" +
                "    when\n" +
                "        // 当客户类型是LOYAL\n" +
                "        OrderRequest(customerType.getValue == \"LOYAL\")\n" +
                "    then\n" +
                "        // 则增加5%的折扣\n" +
                "        System.out.println(\"==========Adding 5% discount for LOYAL customer=============\");\n" +
                "        orderDiscount.setDiscount(orderDiscount.getDiscount() + 5);\n" +
                "end\n" +
                "\n" +
                "rule \"Customer type based discount - others\"\n" +
                "    when\n" +
                "    OrderRequest(customerType.getValue != \"LOYAL\")\n" +
                "then\n" +
                "    System.out.println(\"==========Adding 3% discount for NEW or DISSATISFIED customer=============\");\n" +
                "    orderDiscount.setDiscount(orderDiscount.getDiscount() + 3);\n" +
                "end\n" +
                "\n" +
                "rule \"Amount based discount\"\n" +
                "    when\n" +
                "        OrderRequest(amount > 1000)\n" +
                "    then\n" +
                "        System.out.println(\"==========Adding 5% discount for amount more than 1000$=============\");\n" +
                "    orderDiscount.setDiscount(orderDiscount.getDiscount() + 5);\n" +
                "end\n" +
                "\n" +
                "rule \"vip customerNumber\"\n" +
                "    when\n" +
                "        OrderRequest(customerNumber == \"11111\")\n" +
                "    then\n" +
                "        System.out.println(\"==========Adding 20% discount for vip customerNumber$=============\");\n" +
                "    orderDiscount.setDiscount(orderDiscount.getDiscount() + 20);\n" +
                "end");
        rule.setCreateBy("黄旭伟");
        rule.setCreateTime(new Date());
        rule.setUpdateBy("黄旭伟");
        rule.setUpdateTime(new Date());
        ruleMapper.save(rule);
        long end = System.currentTimeMillis();
        System.out.println("=执行时间=" + (end - start));
    }

    @Test
    void queryRule() {
        long start = System.currentTimeMillis();
        Rule demo1 = ruleMapper.getByName("demo1");
        System.out.println("JSONUtils.toJSONString(demo1) = " + demo1);
        long end = System.currentTimeMillis();
        System.out.println("=执行时间=" + (end - start));
    }
}
