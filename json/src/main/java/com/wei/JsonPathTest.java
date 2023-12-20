package com.wei;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.util.Date;
import java.util.List;

public class JsonPathTest {
    public static void main(String[] args) {
        String s = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";
        // 获取值
        List<String> authors = JsonPath.read(s, "$.store.book[*].author");
        System.out.println("authors = " + authors);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(s);
        // 获取值
        String author0 = JsonPath.read(document, "$.store.book[0].author");
        String author1 = JsonPath.read(document, "$.store.book[1].author");
        System.out.println("author0 = " + author0);
        System.out.println("author1 = " + author1);
        // 解析值
        String json = "{\"date_as_long\" : 9911455611975}";
        Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
        System.out.println(date);
        // 注入值
        String newJson = JsonPath.parse(s).set("$['store']['book'][*]['author']", "huangxuwei").jsonString();
        System.out.println(newJson);
        // 新增值
        BookInfoDTO bookInfoDTO = new BookInfoDTO();
        bookInfoDTO.setCategory("王者不可阻挡");
        bookInfoDTO.setAuthor("黄旭伟");
        bookInfoDTO.setTitle("钢铁是怎样练成的");
        bookInfoDTO.setPrice(22.22D);
        String newAddJson = JsonPath.parse(s).add("$['store']['book']", bookInfoDTO).jsonString();
        authors = JsonPath.read(newAddJson, "$.store.book[*].author");
        System.out.println("authors = " + authors);
        System.out.println(newAddJson);
    }
}
