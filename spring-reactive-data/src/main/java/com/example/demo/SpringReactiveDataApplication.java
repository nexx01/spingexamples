package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "java/com/example/demo/data")
@EnableAutoConfiguration

public class SpringReactiveDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveDataApplication.class, args);
	}

}
