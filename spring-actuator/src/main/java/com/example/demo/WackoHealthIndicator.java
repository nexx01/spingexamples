package com.example.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@ManagedResource
public class WackoHealthIndicator implements HealthIndicator {
    @Override
    @ManagedOperation
    public Health health() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour > 12) {
            return Health
                    .outOfService()
                    .withDetail("reason", "I'm out of service after lunchtime")
                    .withDetail("hour", hour)
                    .build();
        }

        if (Math.random() <= 0.1) {
            return Health
                    .down()
                    .withDetail("reason", "I break 10% of the time")
                    .build();
        }
        return Health
                .up()
                .withDetail("reason", "All is good")
                .build();

    }
}
