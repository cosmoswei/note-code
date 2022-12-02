/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.sink;

import com.wei.pojo.Event;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author huangxuwei
 * @date 2022年10月21日 11:17
 */
public class SinkToElasticSearchTest {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<Event> streamSource = env.fromElements(
                new Event("A1", "/A1", 1000L),
                new Event("A2", "/A2", 2000L),
                new Event("A3", "/A3", 3000L),
                new Event("A4", "/A4", 4000L),
                new Event("A5", "/A5", 5000L),
                new Event("A6", "/A6", 6000L),
                new Event("A7", "/A7", 7000L)
        );

        List<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost("127.0.0.1", 9200, "http"));

        ElasticsearchSinkFunction<Event> elasticsearchSinkFunction = new ElasticsearchSinkFunction<Event>() {
            @Override
            public void process(Event event, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
                HashMap<String, String> data = new HashMap();
                data.put(event.user, event.url);

                IndexRequest request = Requests.indexRequest()
                        .index("clicks")
                        .type("type")
                        .source(data);

                requestIndexer.add(request);
            }
        };

        streamSource.addSink(new ElasticsearchSink.Builder<Event>(httpHosts, elasticsearchSinkFunction).build());

//        streamSource.addSink(esBuilder.build());

        env.execute();
    }
}