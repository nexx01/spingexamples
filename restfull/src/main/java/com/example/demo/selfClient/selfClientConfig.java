package com.example.demo.selfClient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class selfClientConfig {

    @Bean("simpleRestTemplate")
    public RestTemplate getSimpleRestTemplate() {
        return new RestTemplate();
    }

    @Bean("simpleRestTemplate2")
    public RestTemplate getSimpleRestTemplate2() {
        return new RestTemplate();
    }

    @Bean("fromBuilderRestTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().basicAuthentication("user","" +
                "password").build();
    }

    @Bean("AdminbuilderRestTemplate")
    public RestTemplate getRestTemplateAdmin() {
        return new RestTemplateBuilder().basicAuthentication("admin","" +
                "admin").build();
    }
}
