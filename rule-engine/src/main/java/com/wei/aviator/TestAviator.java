package com.wei.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.HashMap;
import java.util.Map;

public class TestAviator {
    public static void main(String[] args) {
        String expression = "a=='河北省'&&((b=='男人'||c>=30)||d=='黄')";

        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);

        Map<String, Object> env = new HashMap<>();
        env.put("a", "河北省");
        env.put("b", "男人");
        env.put("c", 1);
        env.put("d", "黄");

        // 执行表达式
        System.out.println(compiledExp.execute(env));

        //注册函数
        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add(1, 2)"));           // 3.0
        System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)")); // 103.0
    }
}

class AddFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env,
                              AviatorObject arg1, AviatorObject arg2) {
        Number left = FunctionUtils.getNumberValue(arg1, env);
        Number right = FunctionUtils.getNumberValue(arg2, env);
        return new AviatorDouble(left.doubleValue() + right.doubleValue());
    }

    public String getName() {
        return "add";
    }
}
