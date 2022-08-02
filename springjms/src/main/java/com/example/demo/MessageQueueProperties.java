package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mq")
public class MessageQueueProperties {

    /**
     * ActiveMQ writer broker user
     */
    String user;

    /**
     * ActiveMQ writer broker password
     */
    String password;

    /**
     * ActiveMQ server.
     */
    @Data
    public static class Server {

        /**
         * ActiveMQ server hostname.
         */
        Integer port;

        /**
         * ActiveMQ server listening port.
         */
        String host;
    }

    /**
     * ActiveMQ url.
     */
    String url;
}