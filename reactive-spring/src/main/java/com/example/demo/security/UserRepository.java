package com.example.demo.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.UUID;

@ConditionalOnProperty(name = "security.enabled.customReactive",havingValue = "true")

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

    Mono<UserDetails> findByUsername(String username);

    Mono<User> findByEmail(String email);

}
