package com.example.demo.kafka;

import com.example.demo.SomeOrser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "spring.kafka",name = "bootstrap-servers")
public class KafkaOrderMessagingService {

    @Autowired
    private KafkaTemplate<String, SomeOrser> kafkaTemplate;

    public static final String KAFKA_TACO_TOPIC = "tacocloud.orders.topic";

    public void sendOrder(SomeOrser someOrser) {
        kafkaTemplate.send(KAFKA_TACO_TOPIC, someOrser);
    }

    public void sendDefault(SomeOrser someOrser) {
//        kafkaTemplate.send(KAFKA_TACO_TOPIC,someOrser);
        kafkaTemplate.sendDefault(someOrser);
    }


    public void send(SomeOrser someOrser) {

    }
}
