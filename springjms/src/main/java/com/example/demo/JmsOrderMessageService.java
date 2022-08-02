package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsOrderMessageService implements OrderMessagingService {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    public Destination destination;

    @Override
    public void sendOrder(SomeOrser order) {
//        jmsTemplate.send(session -> session.createObjectMessage(order));
//        jmsTemplate.send(destination,session -> session.createObjectMessage(order));
        jmsTemplate.send("tacocloud.order.queue",session -> session.createObjectMessage(order));
    }

    @Override
    public void ConvertAndSendOrder(SomeOrser order) {
        jmsTemplate.convertAndSend(order);
    }

    @Override
    public void convertAndSendOrderNotSerialisable(OrderNotSerializable orderNotSerializable) {
        jmsTemplate.convertAndSend(destination,orderNotSerializable);
    }

    @Override
    public void sendWithAdditionalMessage(SomeOrser order) {
        jmsTemplate.send(destination,session -> {
            Message message = session.createObjectMessage(order);
            message.setStringProperty("X_ORDER_SOURCE","WEB");
            return message;
        });
    }

    @Override
    public void convertandsendWithAdditionalMessage(OrderNotSerializable orderNotSerializable) {
//        jmsTemplate.convertAndSend(destination, orderNotSerializable, new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws JMSException {
//                message.setStringProperty("X_ORDER_SOURCE", "WEB");
//                return message;
//            }
//        });

        jmsTemplate.convertAndSend(destination, orderNotSerializable, message -> {
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }
}
