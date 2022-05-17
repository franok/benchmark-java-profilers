package com.test.benchmarks.alloc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Allocators {

    public ArrayList<String> allocate(int iterations, int maxAllocSizeInKB) {
        Random random = new Random();
        ArrayList<String> bytes = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            byte[] tmp = newByteArray(random, maxAllocSizeInKB);
            bytes.add(Arrays.toString(tmp));
        }
        return bytes;
    }

    private byte[] newByteArray(Random random, int maxAllocSizeInKB) {
//        return new byte[random.nextInt(1024) * 1024];
        return new byte[random.nextInt(1024) * maxAllocSizeInKB];
    }
}
