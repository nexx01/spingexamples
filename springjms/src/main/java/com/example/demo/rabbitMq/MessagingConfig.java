package com.example.demo.rabbitMq;

import lombok.val;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@ConditionalOnProperty(name = "rabbit.enabled",havingValue = "true")
public class MessagingConfig {
    public final static String FANOUT_QUEUE_NAME = "amqp.fanout.queue";
    public final static String FANOUT_EXCHANGE_NAME = "amqp.fanout.exchange";
    public final static String TOPIC_QUEUE_NAME = "amqp.topic.queue";
    public final static String TOPIC_EXCHANGE_NAME = "amqp.topic.exchange";
    public static final String BINDING_PATTERN_ERROR = "#.error";
    private static final boolean NON_DURABLE = false;
    private static final boolean DO_NOT_AUTO_DELETE = false;
    @Bean
    public Declarables topicBindings() {
        val topicQueue = new Queue(TOPIC_QUEUE_NAME, NON_DURABLE);
        val topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME, NON_DURABLE,
                DO_NOT_AUTO_DELETE);
        return new Declarables(topicQueue, topicExchange, BindingBuilder
                .bind(topicQueue)
                .to(topicExchange)
                .with(BINDING_PATTERN_ERROR));
    }
    @Bean
    public Declarables fanoutBindings() {
        val fanoutQueue = new Queue(FANOUT_QUEUE_NAME, NON_DURABLE);
        val fanoutExchange = new FanoutExchange(FANOUT_EXCHANGE_NAME, NON_DURABLE,
                DO_NOT_AUTO_DELETE);
        return new Declarables(fanoutQueue, fanoutExchange, BindingBuilder
                .bind(fanoutQueue)
                .to(fanoutExchange));
    }

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }
//
//    @Override
//    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
//        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
//    }
}