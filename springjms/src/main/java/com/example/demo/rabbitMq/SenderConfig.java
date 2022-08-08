package com.example.demo.rabbitMq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.rabbitMq.RabbitOrderMessagingService.TACOCLOUD_ORDER_QUEUE;

@Configuration
@ConditionalOnProperty(name = "rabbit.enabled",havingValue = "true")

public class SenderConfig {

  //  @Value("${queue.name}")
    private String message = TACOCLOUD_ORDER_QUEUE;

    @Bean
    public Queue queue() {
        return new Queue(message, true);
    }

}