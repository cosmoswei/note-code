package com.wei;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;


/**
 * Spel 组件
 *
 * @author 黄旭伟
 */
public class SpelUtils {

    private SpelUtils() {
    }

    private static final ExpressionParser parser = new SpelExpressionParser();

    public static Object parser(ProceedingJoinPoint proceeding, String spelStr) {
        MethodSignature methodSignature = (MethodSignature) proceeding.getSignature();
        List<String> paramNameList = Arrays.asList(methodSignature.getParameterNames());
        List<Object> paramList = Arrays.asList(proceeding.getArgs());
        EvaluationContext ctx = new StandardEvaluationContext();
        for (int i = 0; i < paramNameList.size(); i++) {
            ctx.setVariable(paramNameList.get(i), paramList.get(i));
        }
        // 解析SpEL表达式获取结果
        return parser.parseExpression(spelStr).getValue(ctx);
    }

}