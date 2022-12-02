/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2022. All Rights Reserved.
 */
package com.wei;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author huangxuwei
 * @date 2022年10月19日 21:06
 */
public class BatchWordCount {
    public static void main(String[] args) throws Exception {

        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> lineDataSource = executionEnvironment.readTextFile("input/word.txt");

        FlatMapOperator<String, Tuple2<String, Long>> wordAndOneTuple = lineDataSource.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String word : words) {
                out.collect(Tuple2.of(word, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        UnsortedGrouping<Tuple2<String, Long>> wordAndOneGroup = wordAndOneTuple.groupBy(0);

        AggregateOperator<Tuple2<String, Long>> sum = wordAndOneGroup.sum(1);

        sum.print();

    }
}