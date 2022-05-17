package com.test.benchmarks.gc.history;

import com.test.benchmarks.gc.GarbageProducer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class GC1MinBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MINUTES)
    @Timeout(time = 3, timeUnit = TimeUnit.MINUTES)
    public void gcBench(Blackhole blackhole) {
        long ONE_MINUTE = 1000 * 60; // 1000ms *60s

        GarbageProducer garbageProducer = new GarbageProducer();

        long start = System.currentTimeMillis();
        long _1minAgo = start - (1000 * 60);

        while (System.currentTimeMillis() - start < ONE_MINUTE) {
            blackhole.consume(garbageProducer.produceGarbage());
            
            long now = System.currentTimeMillis();
            if (now - _1minAgo > 1000 * 60) { // after 1min
                _1minAgo = now;
                System.out.println("elapsed time: " +
                        TimeUnit.MILLISECONDS.toMinutes(now - start) + "min");
            }
        }
    }

}
