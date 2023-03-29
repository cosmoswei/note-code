package com.wei.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

public class Main {
    public static void main(String[] args) {
        // Compile a script
        Expression script = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
        script.execute();
        System.out.println("Hello world!");
    }
}