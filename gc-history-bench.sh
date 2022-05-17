#!/bin/bash

# gc history ${DURATION}

DURATIONS="OneShot 1Min 10Min 20Min 30Min 40Min 50Min 1Hour"

for DURATION in $DURATIONS; do
  ## jfr
  java -jar target/benchmarks.jar GC${DURATION}Benchmark -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+AlwaysPreTouch -XX:+UseG1GC -XX:StartFlightRecording=settings=sapmachine11/lib/jfr/sapmachine-gc-history.jfc,filename=./results/jfr/profiling/$(date +'%FT%H-%M')-jmh-${DURATION}-gc-history.jfr" -rf json -rff results/jfr/$(date +'%FT%H-%M')-jmh-result-${DURATION}-gc-history.json -i 1 -f 1 -wi 0 | tee results/jfr/$(date +'%FT%H-%M')-output-${DURATION}-gc-history.log
done
