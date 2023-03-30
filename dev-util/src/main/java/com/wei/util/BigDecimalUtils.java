package com.wei.util;

import java.math.BigDecimal;

/**
 * BigDecimal 对象比较工具类
 *
 * @author huangxuwei
 * @unit BigDecimalUtilsTest
 * @date 2023年03月14日 14:30
 */
public class BigDecimalUtils {

    private BigDecimalUtils() {
    }

    /**
     * 判断两个 BigDecimal 相等
     *
     * @param var1
     * @param var2
     * @return
     */
    public static boolean eq(BigDecimal var1, BigDecimal var2) {
        notNull(var1, var2);
        return var1.compareTo(var2) == 0;
    }

    /**
     * 判断两个 BigDecimal 不相等
     *
     * @param var1
     * @param var2
     * @return
     */
    public static boolean ne(BigDecimal var1, BigDecimal var2) {
        return !eq(var1, var2);
    }

    /**
     * var1 大于 var2 返回 ture
     *
     * @param var1
     * @param var2
     * @return
     */
    public static boolean gt(BigDecimal var1, BigDecimal var2) {
        notNull(var1, var2);
        return var1.compareTo(var2) > 0;
    }

    /**
     * var1 小于 var2 返回 ture
     *
     * @param var1
     * @param var2
     * @return
     */
    public static boolean lt(BigDecimal var1, BigDecimal var2) {
        notNull(var1, var2);
        return var1.compareTo(var2) < 0;
    }

    /**
     * var1 大于等于 var2 返回 ture
     *
     * @param var1
     * @param var2
     * @return
     */
    public static boolean ge(BigDecimal var1, BigDecimal var2) {
        return !lt(var1, var2);
    }

    /**
     * var1 小于等于 var2 返回 ture
     *
     * @param var1
     * @param var2
     * @return
     */
    public static boolean le(BigDecimal var1, BigDecimal var2) {
        return !gt(var1, var2);
    }

    private static void notNull(Object object1, Object object2) {
        if (object1 == null || object2 == null) {
            throw new NullPointerException("对象不可为空");
        }
    }

}