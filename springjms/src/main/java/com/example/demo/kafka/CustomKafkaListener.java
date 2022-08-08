package com.example.demo.kafka;

import com.example.demo.SomeOrser;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(prefix = "spring.kafka", name = "bootstrap-servers")
public class CustomKafkaListener {

    public static final Logger log = LoggerFactory.getLogger(CustomKafkaListener.class);

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(SomeOrser someOrser) {
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("Received: " + someOrser);
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handleRecord(SomeOrser someOrser, ConsumerRecord<String, SomeOrser> record) {
        log.info("!!!!!!!!!!!!!!!!!!!");
        log.info("Received from partition {} with timestamp {}", record.partition(), record.timestamp());
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handleMessage(SomeOrser orser, Message<SomeOrser> message) {
        MessageHeaders headers = message.getHeaders();
        log.info("Received from partition {} with timestamp {}",
                headers.get(KafkaHeaders.RECEIVED_PARTITION_ID),
                headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));

    }


}
