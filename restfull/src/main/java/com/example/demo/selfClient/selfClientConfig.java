package com.example.demo.selfClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class selfClientConfig {

    @Bean("simpleRestTemplate")
    public RestTemplate getSimpleRestTemplate() {

        return new RestTemplate();
    }
//
//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
////        objectMapper
////                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
////                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//        // objectMapper.registerSubtypes(new NamedType(OrderMessagingService.class));
////        messageConverter.setObjectMapper(objectMapper);
//        messageConverter.setTargetType(MessageType.TEXT);
//        messageConverter.setTypeIdPropertyName("_type");
////https://ichihedge.wordpress.com/2016/05/23/correctly-configuring-mappingjackson2messageconverter-for-messaging-via-json/
////        Map<String, Class<?>> typeIdMapping = new HashMap<>();
////        typeIdMapping.put("order", OrderNotSerializable.class);
////        messageConverter.setTypeIdMappings(typeIdMapping);
//        return messageConverter;
//    }

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

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
        return mapper;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() throws Exception {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        return registrationBean;
    }
}
