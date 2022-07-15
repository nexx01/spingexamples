package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ValidatespringApplication implements CommandLineRunner {

	@Autowired
	PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(ValidatespringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	//	personService.save(new PersonDto());

		personService.custom(new DTOCustomValidate());

	}
}
