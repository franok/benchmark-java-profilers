package com.test.benchmarks.alloc;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class AllocationBenchmark2 {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void allocationBench(Blackhole blackhole) {
        Allocators allocator = new Allocators();
        blackhole.consume(allocator.allocate(20, 20)); // 20KB, assumed to allocate inside TLAB // 10KB on average
    }

}
