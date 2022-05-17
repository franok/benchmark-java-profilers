/* The Computer Language Benchmarks Game
 * https://salsa.debian.org/benchmarksgame-team/benchmarksgame/
 *
 * contributed by Jarkko Miettinen
 * modified by Daryl Griffith
 * *reset*
 *
 * code taken from: https://benchmarksgame-team.pages.debian.net/benchmarksgame/program/binarytrees-java-3.html
 * and modified
 */

package com.test.benchmarks.alloc;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class BinaryTreesBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(time = 30) // 30 seconds per iteration (instead of default: 10s)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void binaryTreesBench() {
        int maxDepth = 21;

        int stretchDepth = maxDepth + 1;

        System.out.println("stretch tree of depth " + stretchDepth +
                "\t check: " + checkTree(createTree(stretchDepth)));

        trees(maxDepth);
    }

    private void trees(int maxDepth) {
        TreeNode longLastingNode = createTree(maxDepth);
        int depth = 4;

        do {
            int iterations = 16 << (maxDepth - depth);

            loops(iterations, depth);
            depth += 2;
        } while (depth <= maxDepth);
        System.out.println("long lived tree of depth " + maxDepth
                + "\t check: " + checkTree(longLastingNode));
    }

    private void loops(int iterations, int depth) {
        int check = 0;
        int item = 0;

        do {
            check += checkTree(createTree(depth));
            item++;
        } while (item < iterations);
        System.out.println(iterations + "\t trees of depth " +
                depth + "\t check: " + check);
    }

    private TreeNode createTree(int depth) {
        TreeNode node = new TreeNode();

        if (depth > 0) {
            depth--;
            node.left = createTree(depth);
            node.right = createTree(depth);
        }
        return node;
    }

    private int checkTree(TreeNode node) {
        if (node.left == null) {
            return 1;
        }
        return checkTree(node.left) + checkTree(node.right) + 1;
    }
}