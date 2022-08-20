package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Buferusation {

    @Test
    void buffer() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);

        Flux<List<Integer>> buffer = just.buffer(3);

        StepVerifier.create(buffer)
                .expectNext(List.of(1, 2, 3))
                .expectNext(List.of(4,5))
                .verifyComplete();
    }

    @Test
    void bufferAndFlatMap() {

        Flux.just(1, 2, 3, 4, 5, 6)
                .buffer(3)
                .flatMap(x -> Flux.fromIterable(x)
                        .map(y -> y * 100)
                        .subscribeOn(Schedulers.parallel())
                        .log())
                .subscribe();
    }

    @Test
    void collectList() {
        Flux<String> just = Flux.just("1", "2", "3", "4", "6");

        Mono<List<String>> listMono = just.collectList();

        StepVerifier.create(listMono)
                .expectNext(Arrays.asList("1", "2", "3", "4", "6"))
                .verifyComplete();
    }

    @Test
    void collectToMap() {

        Flux<String> just = Flux.just("1", "2", "3", "4", "6");

        Mono<Map<String, String>> mapMono =
                just.collectMap(a -> a + 100);

        StepVerifier.create(mapMono)
                .expectNextMatches(map->{
                    return map.size()==5&&
                            map.get("1100").equals("1") &&
                            map.get("2100").equals("2") &&
                            map.get("3100").equals("3") ;
                }).verifyComplete();
    }

    @Test
    void all() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5, 6, 7);

        Mono<Boolean> all = just.all(a -> a < 100);

        StepVerifier.create(all)
                .expectNext(true)
                .verifyComplete();

        Mono<Boolean> all1 = just.all(a -> a > 5);
        StepVerifier.create(all1)
                .expectNext(false)
                .verifyComplete();

    }

    @Test
    void any() {
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);

        Mono<Boolean> any = just.any(a -> a > 4);

        StepVerifier.create(any)
                .expectNext(true)
                .verifyComplete();

    }
}
