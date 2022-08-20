package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.Flushable;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

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

    @Test
    void fromIterable() {
        List<String> strings = List.of("1", "2", "3", "4");

        Flux<String> stringFlux = Flux.fromIterable(strings);

        StepVerifier.create(stringFlux)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .expectNext("4")
                .verifyComplete();

    }

    @Test
    void fromString() {
        Stream<String> stringStream = Stream.of("1", "2", "3", "4");

        Flux<String> stringFlux = Flux.fromStream(stringStream);

        StepVerifier.create(stringFlux)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .expectNext("4")
                .verifyComplete();

    }

    @Test
    void range() {
        Flux<Integer> range = Flux.range(1, 5);

        StepVerifier.create(range)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .verifyComplete();
    }

    @Test
    void interval() {
        Flux<Long> take = Flux.interval(Duration.ofSeconds(1))
                .take(5);

        StepVerifier.create(take)
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(4L);
    }

    @Test
    void generateInfinity() {
        Flux.generate(() -> 1, (state, sink) -> {
                    sink.next(state + 1);
                    return state;
                })
                .take(10)
                .subscribe(System.out::println);


    }
}
