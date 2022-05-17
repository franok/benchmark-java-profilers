#!/bin/bash

# cpu burner

## plain java11
java -jar target/benchmarks.jar CPUBurnerBenchmark1 -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch" -rf json -rff results/plain/java11/$(date +'%FT%H-%M')-jmh-result-cpu-burner.json -i 10 -f 3 -wi 5 -to 5m | tee results/plain/java11/$(date +'%FT%H-%M')-output-cpu-burner.log

## jfr
java -jar target/benchmarks.jar CPUBurnerBenchmark1 -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch -XX:StartFlightRecording=settings=profile,filename=./results/jfr/profiling/$(date +'%FT%H-%M')-jmh-cpu-burner.jfr" -rf json -rff results/jfr/$(date +'%FT%H-%M')-jmh-result-cpu-burner.json -i 10 -f 3 -wi 5 -to 5m | tee results/jfr/$(date +'%FT%H-%M')-output-cpu-burner.log

## async-profiler
java -jar target/benchmarks.jar CPUBurnerBenchmark1 -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch -agentpath:async-profiler-2.5.1-linux-x64/build/libasyncProfiler.so=start,event=cpu,file=./results/async-profiler/profiling/$(date +'%FT%H-%M')-jmh-cpu-burner.html" -rf json -rff results/async-profiler/$(date +'%FT%H-%M')-jmh-result-cpu-burner.json -i 10 -f 3 -wi 5 -to 5m | tee results/async-profiler/$(date +'%FT%H-%M')-output-cpu-burner.log
