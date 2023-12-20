package com.wei;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonValueInjector {
    public static void main(String[] args) throws Exception {
        String jsonString = "{\"bearerList\":[{\"bearerType\":\"VENDOR\",\"bearerAccount\":\"200366\",\"bearerUserId\":\"76880000041581\",\"bearerUserCode\":\"0013282\",\"bearerName\":\"东莞市大岭山德正湘副食商行\",\"bearerRate\":100},{\"bearerType\":\"VENDOR\",\"bearerAccount\":\"202061\",\"bearerUserId\":\"76880000043112\",\"bearerUserCode\":\"0014901\",\"bearerName\":\"广州黄但记电子商务有限公司\",\"bearerRate\":100}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        String targetPath = "bearerList[1].bearerAccount";
        String newValue = "new value";
        injectValue(jsonNode, targetPath, newValue);
        String modifiedJsonString = objectMapper.writeValueAsString(jsonNode);
        System.out.println(modifiedJsonString);
    }

    private static void injectValue(JsonNode jsonNode, String targetPath, String newValue) {
        String[] keys = targetPath.split("\\.");
        JsonNode currentNode = jsonNode;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (key.matches(".+\\[\\d+\\]")) {
                String[] parts = key.split("\\[");
                String arrayKey = parts[0];
                int index = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));

                if (currentNode.has(arrayKey)) {
                    JsonNode arrayNode = currentNode.get(arrayKey);
                    if (arrayNode.isArray() && index < arrayNode.size()) {
                        currentNode = arrayNode.get(index);
                    } else {
                        throw new IllegalArgumentException("Invalid array index: " + key);
                    }
                } else {
                    throw new IllegalArgumentException("Array not found: " + arrayKey);
                }
            } else {
                if (currentNode.has(key)) {
                    currentNode = currentNode.get(key);
                } else {
                    throw new IllegalArgumentException("Key not found: " + key);
                }
            }
        }

        if (currentNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) currentNode;
            String key = keys[keys.length - 1];
            objectNode.put(key, newValue);
        } else {
            throw new IllegalArgumentException("Invalid path: " + targetPath);
        }
    }
}