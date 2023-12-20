package com.wei;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    public static void main(String[] args) throws Exception {
        String jsonString = "{\n" +
                "  \"tmActivityStart\": \"2023-12-09 16:07:48\",\n" +
                "  \"tmActivityEnd\": \"2023-12-09 16:07:48\",\n" +
                "  \"activityRemark\": \"activityRemark_0ac127862a97\",\n" +
                "  \"categoryList\": [\n" +
                "    {\n" +
                "      \"categoryId\": \"categoryId_5b3d1b1556ab\",\n" +
                "      \"categoryName\": \"categoryName_5bb8112ecf29\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"showTicketLabelFlag\": false,\n" +
                "  \"toolType\": \"toolType_27c78821099b\",\n" +
                "  \"toolStepValues\": [\n" +
                "    {\n" +
                "      \"useOrderAmountLimit\": \"useOrderAmountLimit_54b1eb2eb1da\",\n" +
                "      \"singleToolAmount\": \"singleToolAmount_992a9ff58b22\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"activityIssuedLimitAmount\": 0,\n" +
                "  \"userIssuedLimitAmount\": 0,\n" +
                "  \"fundedType\": \"fundedType_bb65d1b73745\",\n" +
                "  \"vendorFunded\": [\n" +
                "    {\n" +
                "      \"type\": \"type_daa8f3234348\",\n" +
                "      \"vendorScale\": \"vendorScale_bef0278964f1\",\n" +
                "      \"vendorCode\": \"vendorCode_507e9b119023\",\n" +
                "      \"vendorName\": \"vendorName_2feb9fd79cc4\",\n" +
                "      \"vendorShortName\": \"vendorShortName_644b649087a2\",\n" +
                "      \"headScale\": \"headScale_89056a0bd9ac\",\n" +
                "      \"provinceScale\": \"provinceScale_3a6bc06564bd\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"vendorWhiteList\": [\n" +
                "    {\n" +
                "      \"vendorCode\": \"vendorCode_5fc977b3f7a2\",\n" +
                "      \"vendorName\": \"vendorName_bb9b1f8a68d4\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"brandWhiteList\": [\n" +
                "    {\n" +
                "      \"brandId\": \"brandId_f7b6dd72fc04\",\n" +
                "      \"brandName\": \"brandName_e35622b585e7\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"saleRegionWhiteList\": [\n" +
                "    {\n" +
                "      \"provinceCode\": \"provinceCode_74254d67d90f\",\n" +
                "      \"provinceName\": \"provinceName_6435a9425a0e\",\n" +
                "      \"cityCode\": \"cityCode_f26196bb2860\",\n" +
                "      \"cityName\": \"cityName_934a6f157e26\",\n" +
                "      \"districtCode\": \"districtCode_37bd2bb51694\",\n" +
                "      \"districtName\": \"districtName_576fb5d41f0a\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"saleRegionBlackList\": [\n" +
                "    {\n" +
                "      \"provinceCode\": \"provinceCode_31237527d7a2\",\n" +
                "      \"provinceName\": \"provinceName_1a751ddecefa\",\n" +
                "      \"cityCode\": \"cityCode_d44c4a665933\",\n" +
                "      \"cityName\": \"cityName_3b1637803b69\",\n" +
                "      \"districtCode\": \"districtCode_66a0f5a09b9a\",\n" +
                "      \"districtName\": \"districtName_55b1d8d62d3f\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"baseMarketAward\": {\n" +
                "    \"invitees\": \"invitees_6ec3cacc9119\",\n" +
                "    \"amount\": \"amount_61081f7c2f08\"\n" +
                "  },\n" +
                "  \"stepMarketAwardList\": [\n" +
                "    {\n" +
                "      \"invitees\": \"invitees_cb65a7aab704\",\n" +
                "      \"amount\": \"amount_0620e85068ba\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"marketAwardFundedType\": \"marketAwardFundedType_c2624dd70700\",\n" +
                "  \"vendorMarketAwardFunded\": [\n" +
                "    {\n" +
                "      \"type\": \"type_6d3c973cfc6e\",\n" +
                "      \"vendorScale\": \"vendorScale_ddb026de5d40\",\n" +
                "      \"vendorCode\": \"vendorCode_677a63cceecc\",\n" +
                "      \"vendorName\": \"vendorName_6482d04f2043\",\n" +
                "      \"vendorShortName\": \"vendorShortName_abc655405c59\",\n" +
                "      \"headScale\": \"headScale_e98c3c208586\",\n" +
                "      \"provinceScale\": \"provinceScale_ce3e00a2cc2b\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"bindActivityId\": 0,\n" +
                "  \"bindActivityName\": \"bindActivityName_7e5dfb8112f6\",\n" +
                "  \"afterSalesEndTime\": \"2023-12-09 16:07:48\",\n" +
                "  \"settlementTime\": \"2023-12-09 16:07:48\",\n" +
                "  \"bindItemList\": [\n" +
                "    {\n" +
                "      \"reportSpuSn\": \"reportSpuSn_90d05cf9cb44\",\n" +
                "      \"reportSkuSn\": \"reportSkuSn_96de2ee41a75\",\n" +
                "      \"skuName\": \"skuName_18a07812366e\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"submissionRuleGroupDetails\": [\n" +
                "    {\n" +
                "      \"groupId\": \"groupId_053ae7072a53\",\n" +
                "      \"groupType\": \"groupType_28c03389aedf\",\n" +
                "      \"groupName\": \"groupName_10a9623d00c1\",\n" +
                "      \"isExclude\": 0,\n" +
                "      \"ruleCenterId\": \"ruleCenterId_ed0800896f57\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"promotionSingle\": {\n" +
                "    \"startLimit\": 0,\n" +
                "    \"endLimit\": 0,\n" +
                "    \"stockLimit\": 0,\n" +
                "    \"amount\": \"amount_c1c653b7bda1\"\n" +
                "  },\n" +
                "  \"tagCode\": \"tagCode_b464d7b85c92\",\n" +
                "  \"fullPieceDiscount\": {\n" +
                "    \"maxAmount\": \"maxAmount_c46567d31588\",\n" +
                "    \"userIssuedLimitAmount\": \"userIssuedLimitAmount_3034dcd2c784\",\n" +
                "    \"discountItemList\": [\n" +
                "      {\n" +
                "        \"quantityLimit\": \"quantityLimit_82c2727bf94f\",\n" +
                "        \"discount\": \"discount_07e81d95e849\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"issueUserNumber\": \"issueUserNumber_4dfbb45fd96e\",\n" +
                "  \"storeScopeType\": \"storeScopeType_af23c06b271a\",\n" +
                "  \"storeScopeValue\": \"storeScopeValue_a6ffc605fa4a\",\n" +
                "  \"storeTagInfo\": {\n" +
                "    \"labelId\": \"labelId_ff16f9409ad6\",\n" +
                "    \"labelName\": \"labelName_22ed207ebf23\",\n" +
                "    \"labelItemId\": \"labelItemId_fec23508c746\",\n" +
                "    \"labelItemName\": \"labelItemName_e6e6bf59302c\"\n" +
                "  },\n" +
                "  \"sourceType\": \"sourceType_3d34a975d222\"\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        String expression = "storeTagInfo.labelItemName";
        String value = getValueByExpression(jsonNode, expression);
        System.out.println(value);
    }

    private static String getValueByExpression(JsonNode jsonNode, String expression) {
        String[] keys = expression.split("\\.");
        JsonNode currentNode = jsonNode;

        for (String key : keys) {
            if (key.contains("[")) {
                int index = getIndexFromKey(key);
                currentNode = currentNode.get(key.substring(0, key.indexOf("["))).get(index);
            } else {
                currentNode = currentNode.get(key);
            }

            if (currentNode == null) {
                return null;
            }
        }

        if (currentNode.isObject()){
            return currentNode.toString();
        }

        if (currentNode.isArray()){
            return currentNode.toString();
        }
        return currentNode.asText();
    }

    private static int getIndexFromKey(String key) {
        // 解析数组下标，例如将 "bearerList[1]" 解析为 1
        String indexStr = key.substring(key.indexOf("[") + 1, key.indexOf("]"));
        return Integer.parseInt(indexStr);
    }
}