package com.example.demo.helloReactiveApplication;

import com.example.demo.OrderRepo;
import com.example.demo.SomeOrder;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import static org.springframework.web
        .reactive.function.server.RouterFunctions.route;
import static org.springframework.web
        .reactive.function.server.ServerResponse.ok;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Objects;

@Configuration
public class HelloReactiveApplication {
//    Разработка API с применением модели
//    функционального программирования Spring предполагает использо-
//    вание следующих четырех основных типов:
    // RequestPredicate – объявляет типы обрабатываемых запросов;
    // RouterFunction  – определяет, как запрос должен передаваться
    //в код обработчика;
    // ServerRequest  – представляет HTTP-запрос, включая заголовки
    //и содержимое;
    // ServerResponse  – представляет HTTP-ответ, включая заголовки
    //и содержимое.

    @Autowired
    OrderRepo orderRepo;

//    @Bean
//    public RouterFunction<?> helloRouterFunction() {
//        RouterFunction<ServerResponse> route = route(GET("/hello"),
//                request -> ok().body(just("Hello World!"),String.class))
//                .andRoute(GET("/buy"),request->ok().body(just("See ya!"),
//                        String.class));
//        return route;
//    }

    @Bean
    @Timed
    public RouterFunction<?> routerFunction() {
        return route(GET("/api/orders")
                .and(queryParam("recent",
                        Objects::nonNull)), this::recents)
                .andRoute(POST("/api/orders"), request1 -> postOrder(request1))
                .andRoute(GET("/api/order/{id}"),request->{
                    SomeOrder someOrder = new SomeOrder("1","2","3");

                    return ServerResponse.ok().body(BodyInserters.fromObject(someOrder));
                });

    }

    @Bean
    public RouterFunction<?> routerFunctionForFlux() {
        return route(GET("/api/allWillBeOutOfMemmory"), request -> {
            return ok().body(fromPublisher(
                    Flux.generate(() -> 1, (STATE, sink) -> {
//                        System.out.println("state: "+STATE );
                        sink.next(STATE + 1);

                        return STATE + 1;
                    })
                    , Integer.class)).delayElement(Duration.ofMillis(
                    400
            ));
        }).andRoute(GET("api/withError"), request ->

            ServerResponse.badRequest()
                    .body(BodyInserters.
                                    fromPublisher(
                                            Mono.error
                                                    (new RuntimeException()),RuntimeException.class)));
    }

    @Bean
    public RouterFunction<?> routerFunctionForFlux2() {
        return    route(GET("/api/all"),request -> {
            Mono<ServerResponse> body = ok().body(BodyInserters.fromPublisher(
                    getPublisher(), Integer.class));
            return body;
        });
    }


    @Bean
    public RouterFunction<?> routerForSimplePostMethod() {
        return route(POST("api/simplePost"), request->{
            return request.bodyToMono(String.class)
                    .map(s->{
                        System.out.println("---11 "+s);
                        return s;
                    })
                    .log()
                    .flatMap(savedTaco -> {
                        return ServerResponse.ok()
                                .body(BodyInserters.
                                        fromPublisher(Mono.just(savedTaco), String.class));
                    });


        });
    }

    public Mono<ServerResponse> recents(ServerRequest request) {
        return ServerResponse.ok()
                .body(orderRepo.findAll().take(12), SomeOrder.class);
    }

    public Flux<Integer> getPublisher() {
        return Flux.generate(() -> 1, (STATE, sink) -> {
//                        System.out.println("state: "+STATE );
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sink.next(STATE + 1);
            System.out.println(STATE);
            if (STATE > 10) {
                sink.complete();
                // throw new RuntimeException();
            }
            return STATE + 1;
        }, System.out::println);
            //   .take(4).cast(Integer.class);
    }


    public Mono<ServerResponse> postOrder(ServerRequest request) {
        return request.bodyToMono(SomeOrder.class)
                .flatMap(taco -> orderRepo.save(taco))
                .flatMap(savedTaco -> {
                    return ServerResponse
                            .created(URI.create(
                                    "http://localhost:8080/api/tacos/" +
                                            savedTaco.getId()))
                            .body(savedTaco, SomeOrder.class);
                });
    }
}
