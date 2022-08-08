package com.example.demo.mail;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.client.RestTemplate;

public class OrderSubmitMessageHandler         implements GenericHandler<EmailOrder> {
    private RestTemplate rest;
    private ApiProperties apiProps;
    public OrderSubmitMessageHandler(ApiProperties apiProps, RestTemplate rest) {


        this.apiProps = apiProps;
        this.rest = rest;
    }
    @Override
    public Object handle(EmailOrder order, MessageHeaders headers) {
        rest.postForObject(apiProps.getUrl(), order, String.class);
        return null;
    }

}
