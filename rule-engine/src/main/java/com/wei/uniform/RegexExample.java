package com.wei.uniform;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexExample {
    public static void main(String[] args) {
        // 正则表达式，匹配不包含英文字母的字符串
        String regex = "^[^A-Za-z]+$";

        // 测试字符串
        String testString1 = "123";
        String testString2 = "abc";
        String testString3 = "123abc";
        String testString4 = "000000";

        // 创建Pattern对象
        Pattern pattern = Pattern.compile(regex);

        // 创建Matcher对象
        Matcher matcher1 = pattern.matcher(testString1);
        Matcher matcher2 = pattern.matcher(testString2);
        Matcher matcher3 = pattern.matcher(testString3);
        Matcher matcher4 = pattern.matcher(testString4);

        // 检查匹配结果
        System.out.println("Is \"" + testString1 + "\" a match? " + matcher1.matches());
        System.out.println("Is \"" + testString2 + "\" a match? " + matcher2.matches());
        System.out.println("Is \"" + testString3 + "\" a match? " + matcher3.matches());
        System.out.println("Is \"" + testString4 + "\" a match? " + matcher4.matches());
    }
}