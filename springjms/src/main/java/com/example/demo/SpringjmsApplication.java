package com.example.demo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableRabbit
@EnableKafka
public class SpringjmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringjmsApplication.class, args);
	}

}

