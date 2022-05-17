#!/bin/bash

# gc profiling

## plain java11
java -jar target/benchmarks.jar GCBenchmark -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch -XX:+UseG1GC" -rf json -rff results/plain/java11/$(date +'%FT%H-%M')-jmh-result-gc-history.json -i 10 -f 3 -wi 5 -to 5m | tee results/plain/java11/$(date +'%FT%H-%M')-output-gc-history.log

## jfr
java -jar target/benchmarks.jar GCBenchmark -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch -XX:+UseG1GC -XX:StartFlightRecording=settings=sapmachine11/lib/jfr/sapmachine-gc-history.jfc,filename=./results/jfr/profiling/$(date +'%FT%H-%M')-jmh-gc-history.jfr" -rf json -rff results/jfr/$(date +'%FT%H-%M')-jmh-result-gc-history.json -i 10 -f 3 -wi 5 -to 5m | tee results/jfr/$(date +'%FT%H-%M')-output-gc-history.log
