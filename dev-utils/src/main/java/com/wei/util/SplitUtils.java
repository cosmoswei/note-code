package com.wei.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义split工具
 *
 * @author huangxuwei
 */
public class SplitUtils {

    private SplitUtils() {
    }

    /**
     * 自定义分割函数，返回第一个
     *
     * @param str   待分割的字符串
     * @param delim 分隔符
     * @return 分割后的第一个字符串
     */
    public static String splitFirst(final String str, final String delim) {
        if (null == str || str.length() == 0 || null == delim) {
            return str;
        }

        int index = str.indexOf(delim);
        if (index < 0) {
            return str;
        }
        if (index == 0) {
            // 一开始就是分隔符，返回空串
            return "";
        }

        return str.substring(0, index);
    }

    /**
     * 自定义分割函数，返回全部
     *
     * @param str   待分割的字符串
     * @param delim 分隔符
     * @return 分割后的返回结果
     */
    public static List<String> split(String str, final String delim) {
        if (null == str) {
            return new ArrayList<>(0);
        }

        if (delim == null || delim.length() == 0) {
            List<String> result = new ArrayList<>(1);
            result.add(str);

            return result;
        }

        final List<String> stringList = new ArrayList<>();
        while (true) {
            int index = str.indexOf(delim);
            if (index < 0) {
                stringList.add(str);
                break;
            }
            stringList.add(str.substring(0, index));
            str = str.substring(index + delim.length());
        }
        return stringList;
    }

    /**
     * 重新返回值：Set
     *
     * @param str   待分割的字符串
     * @param delim 分隔符
     * @return 自定义分割函数，返回全部
     */
    public static Set<String> splitDistinct(String str, final String delim) {
        return new HashSet<>(split(str, delim));
    }

}