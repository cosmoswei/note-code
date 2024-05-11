//package com.wei.es;
//
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.search.ClearScrollRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchScrollRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.geo.GeoPoint;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.BoostingQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.reindex.BulkByScrollResponse;
//import org.elasticsearch.index.reindex.DeleteByQueryRequest;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.bucket.range.Range;
//import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
//import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//class testSmsSearch {
//    String index = "sms_logs_index";
//    String type = "sms_logs_type";
//
//    @Test
//    void test_get_id() throws IOException {
//        GetRequest request = new GetRequest(index, type, "1");
//        RestHighLevelClient client = ESClient.getClient();
//        GetResponse resp = client.get(request, RequestOptions.DEFAULT);
//        System.out.println(resp.getSourceAsMap());
//
//    }
//
//    @Test
//    void test_query_ids() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.idsQuery().addIds("1", "2", "3"));
//        request.source(builder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//
//    @Test
//    void test_query_prefix() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.prefixQuery("smsContext", "河"));
//        request.source(builder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//
//    @Test
//    void test_query_fuzzy() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.fuzzyQuery("corpName", "盒马生鲜").prefixLength(2));
//        request.source(builder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//
//    @Test
//    void test_query_wildcard() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.wildcardQuery("corpName", "*车"));
//        request.source(builder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//
//    @Test
//    void test_query_range() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.rangeQuery("fee").lt(5).gt(2));
//        request.source(builder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//
//    @Test
//    void test_query_regexp() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.regexpQuery("moible", "106[0-9]{8}"));
//        request.source(builder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//
//    @Test
//    void testQueryScroll() throws IOException {
//        // 1   创建SearchRequest
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        // 2   指定scroll信息,生存时间
//        request.scroll(TimeValue.timeValueMinutes(1L));
//        // 3   指定查询条件
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.size(2);
//        builder.sort("fee", SortOrder.DESC);
//        builder.query(QueryBuilders.matchAllQuery());
//        // 4 获取返回结果scrollid ,source
//        request.source(builder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        String scrollId = response.getScrollId();
//        System.out.println(scrollId);
//        while (true) {
//            // 5  循环创建SearchScrollRequest
//            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
//            // 6 指定scrollid生存时间
//            scrollRequest.scroll(TimeValue.timeValueMinutes(1L));
//            // 7 执行查询获取返回结果
//            SearchResponse scrollResp = client.scroll(scrollRequest, RequestOptions.DEFAULT);
//            // 8 判断是否得到数据，输出
//            if (scrollResp.getHits().getHits() != null && scrollResp.getHits().getHits().length > 0) {
//                System.out.println("=======下一页的数据========");
//                for (SearchHit hit : scrollResp.getHits().getHits()) {
//                    System.out.println(hit.getSourceAsMap());
//                }
//            } else {
//                // 9 判断没有查询到数据-退出循环
//                System.out.println("没得");
//                break;
//            }
//        }
//        // 10  创建clearScrollRequest
//        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
//        // 11 指定scrollid
//        clearScrollRequest.addScrollId(scrollId);
//        // 12  删除
//        client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
//    }
//
//    @Test
//    void testDeleteByQueryRequest() throws IOException {
//        DeleteByQueryRequest request = new DeleteByQueryRequest(index);
//        request.types(type);
//
//        request.setQuery(QueryBuilders.rangeQuery("relyTotal").gt("2").lt("3"));
//
//        RestHighLevelClient client = ESClient.getClient();
//        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);
//
//        System.out.println(response.toString());
//    }
//
//    @Test
//    void testBooleanQuery() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder builder = QueryBuilders.boolQuery();
//        builder.should(QueryBuilders.termQuery("province", "武汉"));
//        builder.should(QueryBuilders.termQuery("province", "长沙"));
//        builder.mustNot(QueryBuilders.termQuery("operatorId", "3"));
//        builder.mustNot(QueryBuilders.termQuery("smsContent", "平安"));
//        builder.mustNot(QueryBuilders.termQuery("smsContent", "中国"));
//        RestHighLevelClient client = ESClient.getClient();
//
//        searchSourceBuilder.query(builder);
//        request.source(searchSourceBuilder);
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        System.out.println(response.toString());
//    }
//
//
//    /**
//     * positive 加分
//     * negative 减分
//     * 大于1 返回
//     */
//    @Test
//    void testBoostingQuery() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoostingQueryBuilder builder = QueryBuilders.boostingQuery(
//                        QueryBuilders.matchQuery("", ""),
//                        QueryBuilders.matchQuery("", ""))
//                .negativeBoost(0.5f);
//        RestHighLevelClient client = ESClient.getClient();
//        searchSourceBuilder.query(builder);
//        request.source(searchSourceBuilder);
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        System.out.println(response.toString());
//    }
//
//    @Test
//    void testFilterQuery() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.filter(QueryBuilders.termQuery("corpName", "盒马生鲜"));
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("fee").gt(5));
//        searchSourceBuilder.query(boolQueryBuilder);
//        request.source(searchSourceBuilder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        System.out.println(response.toString());
//    }
//
//    @Test
//    void testHighlightQuery() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.filter(QueryBuilders.termQuery("corpName", "盒马生鲜"));
//        boolQueryBuilder.filter(QueryBuilders.rangeQuery("fee").gt(5));
//        searchSourceBuilder.query(boolQueryBuilder);
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("smsContent", 10)
//                .preTags("<font color = red>")
//                .preTags("</font>");
//        searchSourceBuilder.highlighter(highlightBuilder);
//        request.source(searchSourceBuilder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit : response.getHits().getHits()) {
//            HighlightField smsContent = hit.getHighlightFields().get("smsContent");
//            System.out.println(smsContent);
//        }
//    }
//
//    @Test
//    void testAggCardinalityQuery() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.aggregation(AggregationBuilders.cardinality("agg").field("province"));
//        request.source(searchSourceBuilder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        Cardinality cardinality = response.getAggregations().get("agg");
//        System.out.println(cardinality.getValue());
//    }
//
//    @Test
//    void testAggRangeQuery() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.aggregation(AggregationBuilders.range("agg")
//                .field("fee").addUnboundedTo(5)
//                .addRange(5, 10)
//                .addUnboundedFrom(10));
//        request.source(searchSourceBuilder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        Range range = response.getAggregations().get("agg");
//        for (Range.Bucket bucket : range.getBuckets()) {
//            Object from = bucket.getFrom();
//            Object bucketTo = bucket.getTo();
//            long docCount = bucket.getDocCount();
//            System.out.println("" + from + bucketTo + docCount);
//        }
//    }
//
//
//    @Test
//    void testAggStatsQuery() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.aggregation(AggregationBuilders.extendedStats("agg"));
//        request.source(searchSourceBuilder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        ExtendedStats extendedStats = response.getAggregations().get("agg");
//        double max = extendedStats.getMax();
//        double min = extendedStats.getMin();
//    }
//
//    @Test
//    void testGeoPolygonQuery() throws IOException {
//        SearchRequest request = new SearchRequest(index);
//        request.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        List<GeoPoint> geoPoints = new ArrayList<>();
//        geoPoints.add(new GeoPoint(116.11111, 1.11111));
//        geoPoints.add(new GeoPoint(116.11111, 1.11111));
//        searchSourceBuilder.query(QueryBuilders.geoPolygonQuery("agg", geoPoints));
//        request.source(searchSourceBuilder);
//        RestHighLevelClient client = ESClient.getClient();
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        for (SearchHit hit : response.getHits().getHits()) {
//            System.out.println(hit.getSourceAsMap());
//        }
//    }
//}