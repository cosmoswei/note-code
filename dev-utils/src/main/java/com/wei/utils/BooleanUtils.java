package com.wei.utils;

import com.wei.function.BranchHandle;
import com.wei.function.PresentOrElseHandler;
import com.wei.function.ThrowExceptionFunction;

import java.util.Objects;
import java.util.function.Supplier;

public class BooleanUtils {

    private BooleanUtils() {
    }

    /**
     * 如果参数为true抛出异常
     *
     * @param b
     * @return ThrowExceptionFunction
     **/
    public static ThrowExceptionFunction isTure(boolean b) {

        return (errorMessage) -> {
            if (b) {
                throw new RuntimeException(errorMessage);
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param b
     * @return BranchHandle
     **/
    public static BranchHandle isTureOrFalse(boolean b) {

        return (trueHandle, falseHandle) -> {
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param str
     * @return PresentOrElseHandler
     **/
    public static PresentOrElseHandler<?> isBlankOrNoBlank(String str) {

        return (consumer, runnable) -> {
            if (str == null || str.length() == 0) {
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }

    /**
     * 如果参数为 null 抛出自定义异常
     *
     * @param param
     * @param exceptionSupplier
     * @param <X>
     * @return
     * @throws X
     */
    public static <X extends Throwable> Throwable orElseThrow(boolean param, Supplier<? extends X> exceptionSupplier) throws X {
        if (!param) {
            throw exceptionSupplier.get();
        } else {
            return null;
        }
    }

    /**
     * 如果参数为 true 抛出通用异常
     *
     * @param b
     * @return ThrowExceptionFunction
     */
    public static ThrowExceptionFunction isNull(Object b) {
        return (errorMessage) -> {
            if (Objects.isNull(b)) {
                throw new RuntimeException(errorMessage);
            }
        };
    }
}
