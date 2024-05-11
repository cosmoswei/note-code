//package com.wei.es;
//
//import com.alibaba.druid.support.json.JSONUtils;
//import com.wei.entity.Books;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.support.master.AcknowledgedResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.common.xcontent.json.JsonXContent;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//@Slf4j
//public class ESTest {
//
//    private static final String index = "person";
//
//    private static final String type = "main";
//
//    private static final RestHighLevelClient ES_CLIENT = ESClient.getClient();
//
//    @Test
//    void redisTest() {
//        RestHighLevelClient restHighLevelClient = ESClient.getClient();
//        System.out.println("es");
//    }
//
//    @Test
//    void createIndex() throws IOException {
//        Settings.Builder s = Settings.builder()
//                .put("", "")
//                .put("", "")
//                .put("", "");
//
//        XContentBuilder xContentBuilder = JsonXContent.contentBuilder()
//                .startObject()
//                .startObject("properties")
//                .startObject("name")
//                .field("type", "text")
//                .endObject()
//                .endObject()
//                .endObject();
//
//
//        CreateIndexRequest request = new CreateIndexRequest(index)
//                .settings(s)
//                .mapping(xContentBuilder);
//
//        CreateIndexResponse createIndexResponse = ES_CLIENT.indices().create(request, RequestOptions.DEFAULT);
//        System.out.println(createIndexResponse.toString());
//    }
//
//    @Test
//    void existIndex() throws IOException {
//        GetIndexRequest request = new GetIndexRequest();
//        request.indices();
//        boolean exists = ES_CLIENT.indices().exists(request, RequestOptions.DEFAULT);
//        System.out.println(exists);
//    }
//
//    @Test
//    void deleteIndex() throws IOException {
//        DeleteIndexRequest request = new DeleteIndexRequest();
//        request.indices("index");
//        AcknowledgedResponse response = ES_CLIENT.indices().delete(request, RequestOptions.DEFAULT);
//        System.out.println(response.isAcknowledged());
//    }
//
//    @Test
//    void createDoc() throws IOException {
//        Books books = new Books();
//        books.setBookID(123);
//        String jsonString = JSONUtils.toJSONString(books);
//        IndexRequest request = new IndexRequest(index, type, books.getBookID().toString());
//        request.source(jsonString, XContentType.JSON);
//        IndexResponse response = ES_CLIENT.index(request, RequestOptions.DEFAULT);
//        System.out.println(response.getResult());
//    }
//
//    @Test
//    void updateDoc() throws IOException {
//        Map<String, Object> map = new HashMap<>();
//        map.put("title", "zheyangwanshiba?");
//        Books books = new Books();
//        books.setBookID(123);
//        UpdateRequest request = new UpdateRequest(index, type, books.getBookID().toString());
//        request.doc(map);
//        UpdateResponse response = ES_CLIENT.update(request, RequestOptions.DEFAULT);
//        System.out.println(response.getResult());
//    }
//
//    @Test
//    void bulkCreateDoc() throws IOException {
//        Books books1 = new Books();
//        Books books2 = new Books();
//        Books books3 = new Books();
//        Books books4 = new Books();
//        books1.setBookID(1);
//        books2.setBookID(2);
//        books3.setBookID(3);
//        books4.setBookID(4);
//        String jsonString1 = JSONUtils.toJSONString(books1);
//        String jsonString2 = JSONUtils.toJSONString(books2);
//        String jsonString3 = JSONUtils.toJSONString(books3);
//        String jsonString4 = JSONUtils.toJSONString(books4);
//        BulkRequest request = new BulkRequest();
//        request.add(new IndexRequest(index, type, books1.getBookID().toString()).source(jsonString1, XContentType.JSON));
//        request.add(new IndexRequest(index, type, books2.getBookID().toString()).source(jsonString2, XContentType.JSON));
//        request.add(new IndexRequest(index, type, books3.getBookID().toString()).source(jsonString3, XContentType.JSON));
//        request.add(new IndexRequest(index, type, books4.getBookID().toString()).source(jsonString4, XContentType.JSON));
//        BulkResponse response = ES_CLIENT.bulk(request, RequestOptions.DEFAULT);
//        System.out.println(response);
//    }
//}
