package com.example.demo.rabbitMq;

import com.example.demo.SomeOrser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static com.example.demo.rabbitMq.RabbitOrderMessagingService.TACOCLOUD_ORDER_QUEUE;

@Component
@ConditionalOnProperty(name = "rabbit.enabled",havingValue = "true")

public class RabbitListenerService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receivMessage(SomeOrser orser) {
        System.out.println("------------------");
        System.out.println("order will be received");
        System.out.println("------------------");

        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        Message message = messageConverter.toMessage("order will be received", new MessageProperties());
        rabbitTemplate.send(TACOCLOUD_ORDER_QUEUE,message);
    }

}

