package com.example.demo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@EnableJms
@Configuration
@RequiredArgsConstructor
public class JmsConfig {

    final MessageQueueProperties activemq;

    @Bean
    @Primary
    public ConnectionFactory connectionFactory() {
        var factory = new ActiveMQConnectionFactory(activemq.getUrl(), activemq.getUser(), activemq.getPassword());
//        var factory = new ActiveMQConnectionFactory(activemq.getUser(), activemq.getPassword(), activemq.getUrl());
//        factory..setTrustedPackages(singletonList(Message.class.getPackage().getName()));
//https://stackoverflow.com/questions/36619432/springboot-activemq-how-to-set-trusted-packages
        return factory;
    }

    @Bean
    public MessageQueueProperties getMessageQueueProperties() {
        return new MessageQueueProperties();
    }

    /**
     * Enable listener endpoint annotations.
     * To enable support for @JmsListener.
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setClientId(SpringjmsApplication.class.getName());
        factory.setConcurrency("2-10");

        return factory;
    }
    //JMS Client SSL Configuration
    //https://documentation.softwareag.com/onlinehelp/Rohan/num10-1/10-1_UM_webhelp/index.html#page/um-webhelp/co-jmsclientssl.html

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }

    // Конвертеры сообщений в Spring для наиболее распространенных
    //случаев применения (все они находятся в пакете org.springframework.jms.support.
    //converter)

//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
//        return messageConverter;
//    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        objectMapper
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
       // objectMapper.registerSubtypes(new NamedType(OrderMessagingService.class));
//        messageConverter.setObjectMapper(objectMapper);
        messageConverter.setTargetType(MessageType.TEXT);
        messageConverter.setTypeIdPropertyName("_type");
//https://ichihedge.wordpress.com/2016/05/23/correctly-configuring-mappingjackson2messageconverter-for-messaging-via-json/
//        Map<String, Class<?>> typeIdMapping = new HashMap<>();
//        typeIdMapping.put("order", OrderNotSerializable.class);
//        messageConverter.setTypeIdMappings(typeIdMapping);
        return messageConverter;
    }

}