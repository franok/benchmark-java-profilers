package com.test.benchmarks.gc.history;

import com.test.benchmarks.gc.GarbageProducer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class GC50MinBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MINUTES)
    @Timeout(time = 53, timeUnit = TimeUnit.MINUTES)
    public void gcBench(Blackhole blackhole) {
        long FIFTY_MINUTES = 1000 * 60 * 50; // 1000ms *60s *50m

        GarbageProducer garbageProducer = new GarbageProducer();

        long start = System.currentTimeMillis();
        long _5minAgo = start - (1000 * 60 * 5);

        while (System.currentTimeMillis() - start < FIFTY_MINUTES) {
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
