package com.wei.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.text.SimpleDateFormat;
import java.util.*;


public class Test {

    public static void main(String[] args) {
        // exec执行方式，无需传递Map格式
        String age = "18";
        System.out.println(AviatorEvaluator.exec("'His age is '+ age +'!'", age));

        Map<String, Object> map = new HashMap<>();
        map.put("s1", "123qwer");
        map.put("s2", "123");

        System.out.println(AviatorEvaluator.execute("string.startsWith(s1,s2)", map));

        // execute执行方式，需传递Map格式
        Map<String, Object> map2 = new HashMap<String, Object>();
        map.put("age", "18");
        System.out.println(AviatorEvaluator.execute("'His age is '+ age +'!'", map2));

        test(args);
        test2(args);
        test3(args);
        test4(args);
        test5(args);
        test6(args);


        AviatorEvaluator.execute("nil == nil");  //true
        AviatorEvaluator.execute(" 3> nil");    //true
        AviatorEvaluator.execute(" true!= nil");    //true
        AviatorEvaluator.execute(" ' '>nil ");  //true
        AviatorEvaluator.execute(" a==nil ");   //true,a is null
    }

    public static void test(String[] args) {
        String expression = "a+(b-c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);

        Map<String, Object> env = new HashMap<>();
        env.put("a", 10.3);
        env.put("b", 45);
        env.put("c", 99.100);

        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);

    }

    public static void test2(String[] args) {

        final List<String> list = new ArrayList<>();
        list.add("hello");
        list.add(" world");

        final int[] array = new int[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 3;

        final Map<String, Date> map = new HashMap<>();
        map.put("date", new Date());

        Map<String, Object> env = new HashMap<>();
        env.put("list", list);
        env.put("array", array);
        env.put("map", map);

        System.out.println(AviatorEvaluator.execute("list[0]+':'+array[0]+':'+'today is '+map.date", env));
    }

    public static void test3(String[] args) {
        Map<String, Object> env = new HashMap<>();
        env.put("a", -5);
        String result = (String) AviatorEvaluator.execute("a>0? 'yes':'no'", env);
        System.out.println(result);
    }

    public static void test4(String[] args) {
        String email = "hello2018@gmail.com";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("email", email);
        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);
        System.out.println(username);
    }

    public static void test5(String[] args) {
        User user = new User(1, "jack", "18");
        Map<String, Object> env = new HashMap<>();
        env.put("user", user);

        String result = (String) AviatorEvaluator.execute(" '[user id='+ user.id + ',name='+user.name + ',age=' +user.age +']' ", env);
        System.out.println(result);
    }

    public static void test6(String[] args) {
        Map<String, Object> env = new HashMap<>();
        final Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(date);
        env.put("date", date);
        env.put("dateStr", dateStr);
        System.out.println(date);
        System.out.println(dateStr);

        Boolean result = (Boolean) AviatorEvaluator.execute("date==dateStr", env);
        System.out.println(result);

        result = (Boolean) AviatorEvaluator.execute("date > '2009-12-20 00:00:00:00' ", env);
        System.out.println(result);

        result = (Boolean) AviatorEvaluator.execute("date < '2200-12-20 00:00:00:00' ", env);
        System.out.println(result);

        result = (Boolean) AviatorEvaluator.execute("date ==date ", env);
        System.out.println(result);
    }

}


