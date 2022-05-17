package com.test;

public class Constants {
    public static final int BENCHMARK_ITERATIONS = 1_000; // overwritten by jmh command line flag -i <int>
    public static final int BENCHMARK_FORKS = 3; // overwritten by jmh command line flag -f <int>
    public static final int BENCHMARK_WARMUPS = 200; // overwritten by jmh command line flag -wi <int>
}
