package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Scheduler {
    private final AtomicInteger testGauge;
    private final Counter counter;

    public Scheduler(MeterRegistry meterRegistry) {
        // Counter vs. gauge, summary vs. histogram
        // https://prometheus.io/docs/practices/instrumentation/#counter-vs-gauge-summary-vs-histogram
        testGauge = meterRegistry.gauge("custom_gauge", new AtomicInteger(0));
        counter = meterRegistry.counter("custom_counter");
    }

    @Scheduled(fixedRateString = "1000", initialDelayString = "0")
    public void schedulingTask() {
        testGauge.set(Scheduler.getRandomNumberInRange(0, 100));
    }

    private static int getRandomNumberInRange(int min,
                                              int max) {
        if (min >= max) {
            throw new IllegalCallerException("max must be greater than min");
        }

        return ThreadLocalRandom.current().nextInt((max - min) + 1) + min;
    }


}
