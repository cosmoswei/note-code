/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei.sink;

import com.wei.pojo.Event;
import com.wei.source.ClickSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * @author huangxuwei
 * @date 2022年10月21日 11:17
 */
public class SinkToRedisTest {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.setParallelism(1);

        FlinkJedisPoolConfig config = new FlinkJedisPoolConfig.Builder()
                .setHost("huangxuwei.com")
                .setPassword("huangxuwei")
                .build();

        executionEnvironment.addSource(new ClickSource()).addSink(new RedisSink<>(config, new RedisMapper<Event>() {
            @Override
            public RedisCommandDescription getCommandDescription() {
                return new RedisCommandDescription(RedisCommand.HSET, "clicks");
            }

            @Override
            public String getKeyFromData(Event event) {
                return event.user;
            }

            @Override
            public String getValueFromData(Event event) {
                return event.timestamp.toString();
            }
        }));

        executionEnvironment.execute();
    }
}