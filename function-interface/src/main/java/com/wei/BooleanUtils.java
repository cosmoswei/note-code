package com.wei;

import java.util.Objects;
import java.util.function.Supplier;

public class BooleanUtils {

    private BooleanUtils() {
    }

    /**
     * 如果参数为true抛出异常
     *
     * @param b
     * @return com.example.demo.func.ThrowExceptionFunction
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
     * @return com.example.demo.func.BranchHandle
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
     * @return com.example.demo.func.BranchHandle
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

    public static <X extends Throwable> Throwable orElseThrow(boolean var, Supplier<? extends X> exceptionSupplier) throws X {
        if (!var) {
            throw exceptionSupplier.get();
        } else {
            return null;
        }
    }

    /**
     * 如果参数为true抛出异常
     *
     * @param b
     * @return com.example.demo.func.ThrowExceptionFunction
     **/
    public static ThrowExceptionFunction isNull(Object b) {
        return (errorMessage) -> {
            if (Objects.isNull(b)) {
                throw new RuntimeException(errorMessage);
            }
        };
    }
}
