package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class MergeReactiveStream {

    @Test
    void mergeFluxes() {
        Flux<Integer> just1 = Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> just2 = Flux.just(6, 7, 8)
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        Flux<Integer> mergedFlux = just1.mergeWith(just2);
//Функция mergeWith() не может гарантировать идеального взаимо-
//действия между источниками
        StepVerifier.create(mergedFlux)
                .expectNext(1)
                .expectNext(6)
                .expectNext(2)
                .expectNext(7)
                .expectNext(3)
                .expectNext(8)
                .verifyComplete();
    }

    @Test
    void zipFluxes() {
        Flux<Integer> just1 = Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> just2 = Flux.just(6, 7, 8)
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        Flux<Tuple2<Integer, Integer>> tuple2Flux = just1.zipWith(just2);
        StepVerifier.create(tuple2Flux)
                .expectNextMatches(p->
                        p.getT1().equals(1)&&p.getT2().equals(6))
                .expectNextMatches(p->
                        p.getT1().equals(2)&&p.getT2().equals(7))
                .expectNextMatches(p->
                        p.getT1().equals(3)&&p.getT2().equals(8))
                .verifyComplete();
    }

    @Test
    void zip2Fluxes() {
        Flux<Integer> just1 = Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> just2 = Flux.just(6, 7, 8);

        Flux<Tuple2<Integer, Integer>> tuple2Flux = Flux.zip(just1,just2);
//Функция mergeWith() не может гарантировать идеального взаимо-
//действия между источниками
        StepVerifier.create(tuple2Flux)
                .expectNextMatches(p->
                    p.getT1().equals(1)&&p.getT2().equals(6))
                .expectNextMatches(p->
                        p.getT1().equals(2)&&p.getT2().equals(7))
                .expectNextMatches(p->
                        p.getT1().equals(3)&&p.getT2().equals(8))
                .verifyComplete();
    }

    @Test
    void zipCustomTuppleFluxes() {
        Flux<Integer> just1 = Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> just2 = Flux.just(6, 7, 8)
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        Flux<CustomTuple<Integer, Integer>> tuple2Flux =
                Flux.zip(
                        just1,just2, CustomTuple::new);
//Функция mergeWith() не может гарантировать идеального взаимо-
//действия между источниками
        StepVerifier.create(tuple2Flux)
                .expectNextMatches(p->
                        p.getRight().equals(1)&&p.getLeft().equals(6))
                .expectNextMatches(p->
                        p.getRight().equals(2)&&p.getLeft().equals(7))
                .expectNextMatches(p->
                        p.getRight().equals(3)&&p.getLeft().equals(8))
                .verifyComplete();
    }

    @Test
    void firstWithSignalFlux() {
        Flux<Integer> just1 = Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(500));
        Flux<Integer> just2 = Flux.just(6, 7, 8)
                .delaySubscription(Duration.ofMillis(250));

        Flux<Integer> integerFlux = Flux.firstWithSignal(just1, just2);

        StepVerifier.create(integerFlux)
                .expectNext(6)
                .expectNext(7)
                .expectNext(8)
                .verifyComplete();
    }
}




 class CustomTuple<T,V> {
     T right;
     V left;

     public CustomTuple(T right, V left) {
         this.right = right;
         this.left = left;
     }

     public T getRight() {
         return right;
     }

     public V getLeft() {
         return left;
     }
 }