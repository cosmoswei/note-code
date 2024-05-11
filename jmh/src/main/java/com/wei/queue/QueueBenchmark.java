package com.wei.queue;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;

@State(Scope.Benchmark)
public class QueueBenchmark {

    private static final int BUFFER_SIZE = 1024;
    private Disruptor<ByteBuffer> disruptor;
    private RingBuffer<ByteBuffer> ringBuffer;
    private ArrayBlockingQueue<ByteBuffer> blockingQueue;

    @Setup(Level.Trial)
    public void setup() {
        disruptor = new Disruptor<>(() -> ByteBuffer.allocate(64), // 显式指定Supplier<ByteBuffer>
                BUFFER_SIZE,
                DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
        });
        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();
        blockingQueue = new ArrayBlockingQueue<>(BUFFER_SIZE);
    }

    @TearDown(Level.Trial)
    public void teardown() {
        disruptor.shutdown();
    }

    @Benchmark
    @Threads(1)
    public void disruptorEnqueue(Blackhole bh) {
        ringBuffer.publish(ringBuffer.next());
        bh.consume(null); // needed to prevent JIT compiler optimizations
    }

    @Benchmark
    @Threads(1)
    public void blockingQueueEnqueue(Blackhole bh) throws InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(64); // Example buffer size
        blockingQueue.put(buffer);
        bh.consume(null); // needed to prevent JIT compiler optimizations
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QueueBenchmark.class.getSimpleName())
                .result(LocalDateTime.now() + "QueueBenchmark.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
    // Add more benchmarks for other queue types as needed
}