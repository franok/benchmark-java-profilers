package com.test.benchmarks.gc.history;

import com.test.benchmarks.gc.GarbageProducer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class GC1HourBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MINUTES)
    @Timeout(time = 70, timeUnit = TimeUnit.MINUTES)
    public void gcBench(Blackhole blackhole) {
        long ONE_HOUR = 1000 * 60 * 60; // 1000ms *60s *60m = 1h

        GarbageProducer garbageProducer = new GarbageProducer();

        long start = System.currentTimeMillis();
        long _5minAgo = start - (1000 * 60 * 5);

        while (System.currentTimeMillis() - start < ONE_HOUR) {
            blackhole.consume(garbageProducer.produceGarbage());
            
            long now = System.currentTimeMillis();
            if (now - _5minAgo > 1000 * 60 * 5) { // after 5min
                _5minAgo = now;
                System.out.println("elapsed time: " +
                        TimeUnit.MILLISECONDS.toMinutes(now - start) + "min");
            }
        }
    }

}
