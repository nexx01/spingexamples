package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;

@SpringBootApplication
public class EmailSpringApplication implements CommandLineRunner {

    @Autowired

    EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(EmailSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        emailService.SendSimpleMessage("test.loayl@gmail.com", "", "emailService.SendSimpleMessage(\"\",\"\",\"\");");
    }
}
