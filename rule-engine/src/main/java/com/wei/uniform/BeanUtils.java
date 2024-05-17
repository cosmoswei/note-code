package com.wei.uniform;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanUtils {

    /**
     * 将字符串的首字母大写。
     *
     * @param str 需要处理的字符串。
     * @return 首字母大写的字符串。
     */
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将Java对象的字段和属性转换为Map。
     *
     * @param object Java对象
     * @return Map类型的键值对
     */
    public static Map<String, Object> objectToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 将JSON字符串转换为Map。
     *
     * @param jsonString JSON字符串
     * @return Map类型的键值对
     */
    public static Map jsonToMap(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        String s = "{\n" +
                "  \"requestChannel\": \"Web\",\n" +
                "  \"requestType\": \"POST\",\n" +
                "  \"requestId\": \"123456\",\n" +
                "  \"requestParam\": {\n" +
                "    \"username\": \"JohnDoe\",\n" +
                "    \"password\": \"password123\",\n" +
                "    \"age\": 30\n" +
                "  },\n" +
                "  \"requestParam3\": {\n" +
                "    \"username\": \"huangxuwei\",\n" +
                "    \"password\": \"password123\",\n" +
                "    \"age\": 30\n" +
                "  },\n" +
                "  \"requestParam2\": {\n" +
                "    \"username\": \"JohnDoe\",\n" +
                "    \"password\": \"password123\",\n" +
                "    \"age\": 30\n" +
                "  }\n" +
                "}";
        // 将JSON字符串转换为Map
        Map<String, Object> jsonMap = BeanUtils.jsonToMap(s);
        UniformRequest uniformRequest = mapToObject(jsonMap, UniformRequest.class);
        System.err.println(uniformRequest);
        UniformRequest request = new UniformRequest();
        request.setRequestChannel("Web");
        request.setRequestType("POST");
        request.setRequestId("123456");
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("username", "JohnDoe");
        params.put("password", "password123");
        // 将LinkedHashMap设置为requestParam属性
        request.setRequestParam(params);
        System.out.println(request);
    }

    /**
     * 将Map转换为特定类型的对象。
     *
     * @param map   包含属性值的Map。
     * @param clazz 目标对象的Class。
     * @param <T>   目标对象的类型。
     * @return 转换后的对象。
     * @throws Exception 可能抛出的异常。
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws Exception {
        T instance = clazz.newInstance();
        if (null == map && map.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 尝试获取属性名对应的setter方法
            String methodName = "set" + capitalize(key);
            // 查找匹配的方法
            Method targetMethod = null;
            for (Method method : UniformRequest.class.getMethods()) {
                if (method.getName().equals(methodName) && method.getParameterCount() == 1) {
                    Class<?> parameterType = method.getParameterTypes()[0];
                    if (parameterType.isAssignableFrom(LinkedHashMap.class)) {
                        targetMethod = method;
                        break;
                    }
                }
            }
            if (targetMethod != null) {
                targetMethod.invoke(instance, value);
            }
        }
        return instance;
    }
}
