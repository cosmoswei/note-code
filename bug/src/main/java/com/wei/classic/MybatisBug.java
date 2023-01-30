package com.wei.classic;

public class MybatisBug {
    public static void main(String[] args) {
        // mybatis Ognl 表达式比较问题
        System.out.println(org.apache.ibatis.ognl.OgnlOps.isEqual("", 0)); // true
        System.out.println(org.apache.ibatis.ognl.OgnlOps.isEqual(null, 0)); // false
        System.out.println(org.apache.ibatis.ognl.OgnlOps.isEqual(null, "")); // false
        System.out.println("".equals(0));
    }
}
