package com.example.demo.rabbitMq;

import com.example.demo.OrderNotSerializable;
import com.example.demo.SomeOrser;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {"rabbit.enabled=true"
//        ,
//        "spring.rabbitmq.host=tcp://l:${mq.server.port}",
//        "spring.rabbitmq.port=15673"
//"spring.rabbitmq.host=localhost",


})
@ActiveProfiles("test")
public class RabbitMqTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    WebTestClient webTestClient;

    public static final String TACOCLOUD_ORDER_QUEUE = "tacocloud.order.queue";


    @Container

    public static DockerComposeContainer dockerComposeContainer
            = new DockerComposeContainer(new File("src/test/resources/rabbitMq/docker-compose.yml"))

//            .withExposedService("rabbitmq", 5672, Wait.forListeningPort())
            .withExposedService("rabbitmq", 15672, Wait.forListeningPort())
    ;
//    @ClassRule
//    public static GenericContainer rabbit = new GenericContainer("rabbitmq:3-management")
//            .withExposedPorts(5672, 15672);

    @Test
    void name() {
        webTestClient.put()
                .uri("/api/orders/sendToRabbit")
                .bodyValue(new SomeOrser("1", "2"))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()

                .expectStatus().isOk().expectBody(String.class)
                .isEqualTo("success Send to rabbitMq");

        // System.out.println("----------------");
        Message receive = rabbitTemplate.receive(TACOCLOUD_ORDER_QUEUE);
        System.out.println("---> receive " + receive);
    }
}
