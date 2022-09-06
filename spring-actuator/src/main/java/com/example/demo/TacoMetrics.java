package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

@Component
public class TacoMetrics extends AbstractRepositoryEventListener<SomeEntity> {

    MeterRegistry meterRegistry;

    public TacoMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }


    @Override
    protected void onAfterCreate(SomeEntity entity) {
        meterRegistry.counter("someEntity", String.valueOf(entity.integer)).increment();
    }
}
