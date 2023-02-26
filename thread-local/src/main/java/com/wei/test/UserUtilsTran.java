package com.wei.test;

import com.alibaba.ttl.TransmittableThreadLocal;

public class UserUtilsTran {
    private static final TransmittableThreadLocal<String> userLocal = new TransmittableThreadLocal<>();

    public static String getUserId(){
        return userLocal.get();
    }
    public static void  setUserId(String userId){
        userLocal.set(userId);
    }

    public static void clear(){
        userLocal.remove();
    }
}
