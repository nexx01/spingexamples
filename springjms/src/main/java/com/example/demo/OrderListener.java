package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//@Profile("jms-listener")
@Component
@Slf4j
@ConditionalOnProperty(name = "artemis.listener.enabled",havingValue = "true")
public class OrderListener {


    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(OrderNotSerializable orderNotSerializable) {
        log.error("-----------------------");
        log.error("-----------------------");
        log.error("-----------------------");
        log.error(orderNotSerializable.nameCus + " " +
                orderNotSerializable);
    }
}
