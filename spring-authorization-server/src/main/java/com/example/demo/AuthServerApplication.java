package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
//@EnableJpaRepositories("com.example.demo")
public class AuthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthServerApplication.class, args);
  }

  @Bean
  public ApplicationRunner dataLoader(
          UserRepository repo, PasswordEncoder encoder) {
    return args -> {
      repo.save(
          new User("habuma", encoder.encode("password"), "ROLE_ADMIN"));
      repo.save(
          new User("tacochef", encoder.encode("password"), "ROLE_ADMIN"));
    };
  }

}
