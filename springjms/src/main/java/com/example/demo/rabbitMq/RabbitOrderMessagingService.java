package com.example.demo.rabbitMq;

import com.example.demo.SomeOrser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "rabbit.enabled",havingValue = "true")

public class RabbitOrderMessagingService {

    public static final String TACOCLOUD_ORDER_QUEUE = "tacocloud.order.queue";
    @Autowired(required = false)
    private RabbitTemplate rabbitTemplate;

    public void sendOrderInRabbit(SomeOrser orderNotSerializable) {
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        Message message = messageConverter.toMessage(orderNotSerializable, messageProperties);
        rabbitTemplate.send(TACOCLOUD_ORDER_QUEUE,message);
    }

    public SomeOrser receiveAndConvertRabbit(SomeOrser someOrser) {
//        SomeOrser someOrser1 = (SomeOrser) rabbitTemplate.receiveAndConvert(TACOCLOUD_ORDER_QUEUE,
//                new ParameterizedTypeReference<SomeOrser>() {
//                });
        SomeOrser someOrser1 = (SomeOrser) rabbitTemplate.receiveAndConvert(TACOCLOUD_ORDER_QUEUE);
        return
        someOrser1;
    }

}
