package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication

//@EnableJpaRepositories("the.package.to.your.repository")
//@EnableJpaRepositories("the.package.to.your.repository")
public class ReactiveSpringApplication {
	@Bean
	public WebClient webClient() {
		return WebClient.create("http://localhost:8080");
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringApplication.class, args);
	}

}
