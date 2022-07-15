package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication

@EnableWebSecurity
public class RestfullApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(RestfullApplication.class, args);
		} catch (Throwable throwable) {

			throwable.printStackTrace();
		}
	}

}
