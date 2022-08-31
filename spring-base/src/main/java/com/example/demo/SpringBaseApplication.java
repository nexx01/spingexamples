package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class SpringBaseApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBaseApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		String databaseUrl = environment.getProperty("database.url");
        boolean containsPassword = environment.containsProperty("database.password");

		System.out.println("---------------------------");
		System.out.println("databaseUrl from environment: "+ databaseUrl);
		System.out.println("containsPassword from environment: "+ containsPassword);
		System.out.println("---------------------------");

	}

}
