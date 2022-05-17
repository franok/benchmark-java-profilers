package com.test.benchmarks.cpu;

import org.openjdk.jmh.infra.Blackhole;

public class Burners {

    public double burnCPU() { // roughly executes for 1.2 - 2.4 ms
        double value = 1.0;

        double result = expensiveCalculation1(value);
        result = expensiveCalculation2(result + Math.random());
        if (result == 0.0) {
            System.out.println("Value is 0.0");
        }
        return result;
    }

    private double expensiveCalculation1(double value) {
        for (int i = 0; i < 100_000; i++) {
            value += value * 1234.0 - value / 36.5 + Math.exp(2.1 + value / (value - 1.0)) + Math.log10(value);
        }
        return value;
    }

    private double expensiveCalculation2(double value) {
        for (int i = 0; i < 10_000; i++) {
            value += value * 5678.0 - value / 36.5 + Math.exp(2.1 + value / (value - 1.0)) + Math.log10(value);
        }
        return value;
    }

    //*************

    public void burnCPU2() {
        Blackhole.consumeCPU(1_000_000);
    }
}
