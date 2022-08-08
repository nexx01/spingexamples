package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.Flushable;

public class CreateReactiveStream {

    @Test
    void just() {
        Flux<String> just = Flux.just("1", "2", "3");

        just.subscribe(System.out::println);

        StepVerifier.create(just)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .verifyComplete();
    }

    @Test
    void fromArray() {
        Flux<String> stringFlux = Flux.fromArray(new String[]{"1", "2", "3", "4"});

        StepVerifier.create(stringFlux)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .expectNext("4")
                .verifyComplete();
    }
}
