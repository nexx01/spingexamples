package com.example.demo;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.TestPropertySource;

import javax.jms.JMSException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = {"artemis.listener.enabled=true"})
public class JmsReceiversTest extends SpringjmsApplicationTest {

    @Autowired
    JmsOrderReceiver receiver;

    @Autowired
    JmsTemplate jmsTemplate;

    String testQueue = "tacocloud.order.queue";

    @BeforeEach
    public void setUp() {

    }

    @Test
    void receive() throws JMSException {
        OrderNotSerializable order = new OrderNotSerializable("1", "2");

        jmsTemplate.convertAndSend(testQueue, order);

        OrderNotSerializable receive = receiver.receiveOrder();

        assertEquals(order.nameCus, receive.nameCus);
        assertEquals(order.surname, receive.surname);
    }

    @Test
    void receiveAndConvert() throws JMSException {
        OrderNotSerializable order = new OrderNotSerializable("1", "2");

        jmsTemplate.convertAndSend(testQueue, order);

        OrderNotSerializable receive = receiver.receiveAndConvert();

        assertEquals(order.nameCus, receive.nameCus);
        assertEquals(order.surname, receive.surname);
    }

    @Test
    void jmsListener() throws JMSException, InterruptedException {
        Logger stubLogger = (Logger) LoggerFactory.getLogger(OrderListener.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        stubLogger.addAppender(listAppender);


        OrderNotSerializable order = new OrderNotSerializable("1", "2");
        jmsTemplate.convertAndSend(testQueue, order);

        List<ILoggingEvent> logsList = listAppender.list;

        assertEquals(logsList.get(0).getFormattedMessage(),"-----------------------");
        assertEquals(logsList.get(1).getFormattedMessage(),"-----------------------");
        assertEquals(logsList.get(2).getFormattedMessage(),"-----------------------");
       // assertEquals(logsList.get(3).getFormattedMessage(),"\"nameCus\":\"1\",\"surname\":\"2\"");
    }


}
