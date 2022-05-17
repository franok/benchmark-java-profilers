#!/bin/bash

# allocation

## plain java11
java -jar target/benchmarks.jar AllocationBenchmark1 -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch" -rf json -rff results/plain/java11/$(date +'%FT%H-%M')-jmh-result-allocator.json -i 10 -f 3 -wi 5 -to 5m | tee results/plain/java11/$(date +'%FT%H-%M')-output-allocator.log

## plain java17
sapmachine17/bin/java -jar target/benchmarks.jar AllocationBenchmark1 -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch" -rf json -rff results/plain/java17/$(date +'%FT%H-%M')-jmh-result-allocator.json -i 10 -f 3 -wi 5 -to 5m | tee results/plain/java17/$(date +'%FT%H-%M')-output-allocator.log

## jfr
java -jar target/benchmarks.jar AllocationBenchmark1 -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch -XX:StartFlightRecording=settings=profile,filename=./results/jfr/profiling/$(date +'%FT%H-%M')-jmh-allocator.jfr" -rf json -rff results/jfr/$(date +'%FT%H-%M')-jmh-result-allocator.json -i 10 -f 3 -wi 5 -to 5m | tee results/jfr/$(date +'%FT%H-%M')-output-allocator.log

## jfr java17
sapmachine17/bin/java -jar target/benchmarks.jar AllocationBenchmark1 -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch -XX:StartFlightRecording=settings=profile,filename=./results/jfr/profiling/$(date +'%FT%H-%M')-jmh-allocator-java17.jfr" -rf json -rff results/jfr/$(date +'%FT%H-%M')-jmh-result-allocator-java17.json -i 10 -f 3 -wi 5 -to 5m | tee results/jfr/$(date +'%FT%H-%M')-output-allocator-java17.log

## async-profiler
java -jar target/benchmarks.jar AllocationBenchmark1 -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch -agentpath:async-profiler-2.5.1-linux-x64/build/libasyncProfiler.so=start,event=alloc,file=./results/async-profiler/profiling/$(date +'%FT%H-%M')-jmh-allocator.html" -rf json -rff results/async-profiler/$(date +'%FT%H-%M')-jmh-result-allocator.json -i 10 -f 3 -wi 5 -to 5m | tee results/async-profiler/$(date +'%FT%H-%M')-output-allocator.log
