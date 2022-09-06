package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@AllArgsConstructor
@EnableJpaRepositories(basePackages = "com.example.demo")
public class SpringActuatorApplication implements CommandLineRunner {

	@Autowired
	SomeEntityRepository someEntityRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringActuatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		someEntityRepository.save(new SomeEntity(1, "first"));
		someEntityRepository.save(new SomeEntity(2, "second"));
	}
}
