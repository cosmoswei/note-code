package com.wei;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

public class JavaAssistTest {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.wei.TestUser");
        CtMethod sayHello = ctClass.getDeclaredMethod("sayHello");
        sayHello.setBody("{return \"hello huangxuwei\";}");
        sayHello.insertBefore("{System.out.println(System.currentTimeMillis());}");

        CtMethod sayHi = CtMethod.make(" public String sayHi(String name){ return \"name\";}", ctClass);
        sayHi.insertBefore("{System.out.println(java.util.Arrays.toString($args));}");
        ctClass.addMethod(sayHi);
        ctClass.writeFile();
        ctClass.toClass();

        // 这个TestUser 跟上面那个TestUser不是同一个类加载器?
        System.out.println("ctClass.getClass().getClassLoader() = " + ctClass.getClass().getClassLoader());
        Class<?> aClass = Class.forName("com.wei.TestUser");
        System.out.println("aClass.getClassLoader() = " + aClass.getClassLoader());
        Method sayHi1 = aClass.getMethod("sayHi", String.class);
        sayHi1.setAccessible(true);
        System.out.println("sayHi1.invoke(\"cosmoswei\") = " + sayHi1.invoke(aClass.newInstance(), ""));
        System.out.println("new TestUser().sayHello() = " + new TestUser().sayHello());
    }
}
