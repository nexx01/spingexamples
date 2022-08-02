package com.example.demo;
import javax.jms.JMSException;
import javax.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
@Component
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jms;
    private MessageConverter converter;

    String destinationName = "tacocloud.order.queue";

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms, MessageConverter converter) {
        this.jms = jms;
        this.converter = converter;
    }
    public OrderNotSerializable receiveOrder() throws JMSException {
        Message message = jms.receive(destinationName);
        return (OrderNotSerializable) converter.fromMessage(message);
    }

    public OrderNotSerializable receiveAndConvert() {
        return (OrderNotSerializable) jms.receiveAndConvert(destinationName);
    }
}
