package com.wei.test;

public class UserUtils {
    private static final ThreadLocal<String> userLocal = new ThreadLocal<>();

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
