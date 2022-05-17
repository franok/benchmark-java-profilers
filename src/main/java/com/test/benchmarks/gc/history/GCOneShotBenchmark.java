package com.test.benchmarks.gc.history;

import com.test.benchmarks.gc.GarbageProducer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class GCOneShotBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MINUTES)
    @Timeout(time = 3, timeUnit = TimeUnit.MINUTES)
    public void gcBench(Blackhole blackhole) {

        GarbageProducer garbageProducer = new GarbageProducer();

        long start = System.currentTimeMillis();

        blackhole.consume(garbageProducer.produceGarbage());

        System.out.println("elapsed time: " +
                TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start) + "ms");
    }

}
