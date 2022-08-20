package com.example.demo;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@Configuration

public class SelfClient implements CommandLineRunner {

    @Autowired
    WebClient webClient;




    @Override
    public void run(String... args) throws Exception {
//        retrieveExample();
        exchangeExample();

    }

    private void exchangeExample(){
        webClient.get()
                .uri("/api/order/{id}", 1)
                .exchangeToMono(cr -> cr.bodyToMono(SomeOrder.class))
                .doOnSuccess(res-> System.out.println("1)--->exchangeBodyToMono->"+res))
                .subscribe();


    }

    private void retrieveExample() {
        SomeOrder block = WebClient.create()
                .get()
                .uri("http://localhost:8080/api/order/{id}", 1)
                .retrieve()
                .bodyToMono(SomeOrder.class).block();

        System.out.println("s-> System.out.println(\"wwwwww \"+ s)s-> System.out.println(\"wwwwww \"+ s) " + block.name);

        WebClient.create()
                .get()
                .uri("http://localhost:8080/api/order/{id}", 1)
                .retrieve()
                .bodyToMono(SomeOrder.class).subscribe(s -> System.out.println(s + " id:" + s.id));

//        webClient.get()
//                .uri("/api/allWillBeOutOfMemmory")
//                .retrieve()
//                .bodyToFlux(Integer.class)
//                .take(1)
//                .subscribe(someOrder ->
//                        System.out.println(
//                                "webclient flux--> "+someOrder + " id:" + someOrder
//                        ));

        webClient.get()
                .uri("/api/all")
                .retrieve()
                .bodyToFlux(Integer.class)
                //.take(1)
                .subscribe(someOrder ->
                        System.out.println(
                                "webclient flux--> "+someOrder + " id:" + someOrder
                        ));

        Mono<String> just = Mono.just("monoElement");
        webClient.post()
                .uri("/api/simplePost")
                .body(just, String.class)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();

        webClient.get()
                .uri("api/withError")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println, x ->
                        System.out.println("in error handler" +x));
    }
}
