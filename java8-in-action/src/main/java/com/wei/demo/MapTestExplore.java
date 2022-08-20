package com.wei.demo;

import java.util.HashMap;
import java.util.Map;

public class MapTestExplore {
    public static void main(String[] args) {
        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("name","huangxuwei");
        hashMap1.put("age","22");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("name","cosmos");
        hashMap2.put("age","25");
        boolean b = hashMap2.keySet().containsAll(hashMap1.keySet());
        boolean b1 = checkTwiceMapKeyIsEquals(hashMap1, hashMap2);
        System.out.println(b);
        System.out.println(b1);
    }
    private static boolean checkTwiceMapKeyIsEquals(Map<String,String> map1, Map<String, String> map2){
        return map2.keySet().containsAll(map1.keySet());
    }
}
