package com.test.benchmarks.alloc;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import static com.test.Constants.*;

public class AllocationBenchmark1 {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void allocationBench(Blackhole blackhole) {
        Allocators allocator = new Allocators();
        blackhole.consume(allocator.allocate(20, 1024)); // 1MB, assumed to allocate outside TLAB // 500KB on average
    }

}
