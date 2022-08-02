package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfiguration {
    @Bean
    public SimpleMailMessage templatrSimpeMessage() {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText("" +
                "This is the test email template for your email:\n%s\n");
        return simpleMailMessage;
    }


}
