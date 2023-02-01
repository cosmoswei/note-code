package com.wei.extensions.java.lang.String;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.apache.commons.lang.StringUtils;

/**
 * String 的扩展方法
 */
@Extension
public final class StringExt {

    public static String[] split(@This String str, char separator) {

        return StringUtils.split(str, separator);
    }
}