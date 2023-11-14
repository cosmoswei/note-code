package com.wei.es;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class createSmsIndex {
    String index = "sms_logs_index";
    String type = "sms_logs_type";

    @Test
    public void create_index() throws IOException {
        Settings.Builder settings = Settings.builder()
                .put("number_of_shards", 3)
                .put("number_of_replicas", 1);

        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                .startObject("properties")
                .startObject("createDate")
                .field("type", "date")
                .field("format", "yyyy-MM-dd")
                .endObject()
                .startObject("sendDate")
                .field("type", "date")
                .field("format", "yyyy-MM-dd")
                .endObject()
                .startObject("longCode")
                .field("type", "keyword")
                .endObject()
                .startObject("mobile")
                .field("type", "keyword")
                .endObject()
                .startObject("corpName")
                .field("type", "keyword")
                .endObject()
                .startObject("smsContent")
                .field("type", "text")
                .field("analyzer", "ik_max_word")
                .endObject()
                .startObject("state")
                .field("type", "integer")
                .endObject()
                .startObject("operatorid")
                .field("type", "integer")
                .endObject()
                .startObject("province")
                .field("type", "keyword")
                .endObject()
                .startObject("ipAddr")
                .field("type", "ip")
                .endObject()
                .startObject("replyTotal")
                .field("type", "integer")
                .endObject()
                .startObject("fee")
                .field("type", "long")
                .endObject()
                .endObject()
                .endObject();

        CreateIndexRequest request = new CreateIndexRequest(index)
                .settings(settings)
                .mapping(type, mappings);

        RestHighLevelClient client = ESClient.getClient();
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    @Test
    public void exists() throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(index);

        RestHighLevelClient client = ESClient.getClient();
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void testQuery() throws IOException {
        // 1 创建Request对象
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        // 2 指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.termQuery("province", "河北"));

        request.source(builder);
        // 3 执行查询
        RestHighLevelClient client = ESClient.getClient();
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4  获取到_source中的数据
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> result = hit.getSourceAsMap();
            System.out.println(result);
        }
    }

    @Test
    void test_terms() throws IOException {
        SearchRequest request = new SearchRequest(index);
        request.types(type);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termsQuery("province", "河北", "河南"));

        request.source(builder);

        RestHighLevelClient client = ESClient.getClient();
        SearchResponse resp = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : resp.getHits().getHits()) {
            System.out.println(hit);
        }
    }

    @Test
    void test_match_all() throws IOException {
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
        RestHighLevelClient client = ESClient.getClient();
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit);
        }

    }

    @Test
    void test_match_field() throws IOException {
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("smsContext", "打车"));
        request.source(builder);
        RestHighLevelClient client = ESClient.getClient();
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit);
        }

    }

    @Test
    void test_match_boolean() throws IOException {
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("smsContext", "打车 女士").operator(Operator.AND));
        request.source(builder);
        RestHighLevelClient client = ESClient.getClient();
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit);
        }

    }

    @Test
    void test_multi_match() throws IOException {
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //builder.query(QueryBuilders.matchQuery("smsContext","打车 女士").operator(Operator.AND));
        builder.query(QueryBuilders.multiMatchQuery("河北", "province", "smsContext"));
        request.source(builder);
        RestHighLevelClient client = ESClient.getClient();
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit);
        }
    }
}