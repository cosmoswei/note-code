package com.wei.extensions.java.util.List;

import manifold.ext.rt.api.Extension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * List 扩展方法
 */
@Extension
public final class ListExt {

    /**
     * 返回只包含一个元素的不可变 List
     */
    @Extension
    public static <E> List<E> of(E element) {
        return Collections.singletonList(element);
    }

    /**
     * 返回包含多个元素的不可变 List
     */
    @Extension
    @SafeVarargs
    public static <E> List<E> of(E... elements) {
        return Collections.unmodifiableList(Arrays.asList(elements));
    }
}