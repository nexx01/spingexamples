package com.example.demo.rabbitMq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@Configuration
@ConditionalOnProperty(name = "rabbit.enabled",havingValue = "true")

public class RabbitConfig {

    @Bean
    @Primary
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

//    @Bean
//    public void classMapper(RabbitTemplate rabbitTemplate, Jackson2JsonMessageConverter converter) {
//        rabbitTemplate.setMessageConverter(converter);
//    }
}
