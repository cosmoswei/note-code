package com.wei.util;

import com.wei.util.function.*;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author PC
 */
public class BooleanUtils {

    private BooleanUtils() {
    }

    /**
     * 如果参数为 false 抛出异常
     *
     * @param param
     * @return ThrowExceptionFunction
     **/
    public static ThrowExceptionFunction orThrow(boolean param) {

        return message -> {
            if (!param) {
                throw new RuntimeException(message);
            }
        };
    }

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param b
     * @return BranchHandle
     **/
    public static BranchHandler isTureOrFalse(boolean b) {

        return (trueHandle, falseHandle) -> {
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    /**
     * 参数为true时，进行的操作
     *
     * @param b
     * @return BooleanHandler
     **/
    public static BooleanHandler isTure(boolean b) {

        return handler -> {
            if (b) {
                handler.run();
            }
        };
    }

    /**
     * 参数为false时，进行的操作
     *
     * @param b
     * @return BooleanHandler
     **/
    public static BooleanHandler isFalse(boolean b) {

        return handler -> {
            if (!b) {
                handler.run();
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
     * 如果参数为 false 抛出自定义异常
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
     * 如果参数为 null 抛出通用异常
     *
     * @param b
     * @return ThrowExceptionFunction
     */
    public static ThrowExceptionFunction isNull(Object b) {
        return errorMessage -> {
            if (Objects.isNull(b)) {
                throw new RuntimeException(errorMessage);
            }
        };
    }

    /**
     * 如果参数为 false 抛出自定义异常
     *
     * @param param
     * @param exceptionSupplier
     * @return
     */
    public static <T, R> ThrowFunction<T> orThrow(boolean param, Function<T, R> exceptionSupplier) {
        return message -> {
            if (!param) {
                exceptionSupplier.apply(message);
            }
        };
    }
}
