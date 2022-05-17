package com.test.benchmarks.cpu;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.test.Constants.BENCHMARK_ITERATIONS;
import static com.test.Constants.BENCHMARK_WARMUPS;

public class ManualCPUBurnerBenchmark {

    public static void main(String[] args) {
        ArrayList<Long> warmupDurations = new ArrayList<>();
        ArrayList<Long> actualDurations = new ArrayList<>();
        double result = 0.0;
        for (int i = 0; i < BENCHMARK_WARMUPS + BENCHMARK_ITERATIONS; i++) {
            long start = System.nanoTime();
            Burners cpuBurner = new Burners();
            result += cpuBurner.burnCPU();
            long end = System.nanoTime();
            if (i < BENCHMARK_WARMUPS) {
                warmupDurations.add(end - start);
                continue;
            }
            actualDurations.add(end - start);
        }

        System.out.println("Result: " + result);
        double warmupMean = mean(warmupDurations);
        double warmupStandardDeviation = standardDeviation(warmupDurations, warmupMean);

        double iterationsMean = mean(actualDurations);
        double iterationsStandardDeviation = standardDeviation(actualDurations, iterationsMean);

        System.out.println("Warm-up mean: " + warmupMean + " ns " +
                "(" + new DecimalFormat("#.###").format(warmupMean/1_000_000) + " ms)");
        System.out.println("Warm-up standard deviation: " + warmupStandardDeviation + " ns " +
                "(" + new DecimalFormat("#.###").format(warmupStandardDeviation/1_000_000) + " ms)");
        System.out.println("Iterations mean: " + iterationsMean + " ns " +
                "(" + new DecimalFormat("#.###").format(iterationsMean/1_000_000) + " ms)");
        System.out.println("Iterations standard deviation: " + iterationsStandardDeviation + " ns " +
                "(" + new DecimalFormat("#.###").format(iterationsStandardDeviation/1_000_000) + " ms)");
        //Todo: error rate?
    }

    public static double mean(ArrayList<Long> numericArray) {
        double sum = numericArray.stream().reduce(0L, Long::sum);
        long length = numericArray.size();
        return sum / length;
    }

    public static double standardDeviation(ArrayList<Long> numericArray, double mean) {
        double variance = numericArray.stream()
                .mapToDouble(num -> num)
                .map(num -> Math.pow(num - mean, 2))
                .sum();

        return Math.sqrt(variance / numericArray.size());
    }


}
