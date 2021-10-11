package com.geekbrains.ru.springproduct.actuator;

import org.apache.catalina.Host;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator  implements HealthIndicator {

    @Override
    public Health health() {
       String message = "Host is ";
        int errorCode = 0;


        if (errorCode != 0) {
            return Health.down().withDetail(message, "not available").build();
        }

        return Health.up().withDetail(message, "available").build();

    }
}
