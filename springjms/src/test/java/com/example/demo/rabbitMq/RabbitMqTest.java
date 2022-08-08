package com.example.demo.rabbitMq;

import com.example.demo.OrderNotSerializable;
import com.example.demo.SomeOrser;
import io.netty.handler.ssl.SslHandler;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
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

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {"rabbit.enabled=true",
        "spring.rabbitmq.host=localhost",
        "spring.rabbitmq.port=5672",
        "spring.rabbitmq.username=guest",
        "spring.rabbitmq.password=guest",
})
@ActiveProfiles("test")
public class RabbitMqTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    WebTestClient webTestClient;

    public static final String TACOCLOUD_ORDER_QUEUE = "tacocloud.order.queue";


//    @Container
//    public static DockerComposeContainer dockerComposeContainer
//            = new DockerComposeContainer(new File("src/test/resources/rabbitMq/docker-compose.yml"))
//
////            .withExposedService("rabbitmq", 5672, Wait.forListeningPort())
//            .withExposedService("rabbitmq", 15672, Wait.forListeningPort())
//    ;
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
                .expectStatus().isCreated().expectBody(String.class)
                .isEqualTo("success Send to rabbitMq");

        // System.out.println("----------------");
        Message receive = rabbitTemplate.receive(TACOCLOUD_ORDER_QUEUE);
        assertNotNull(receive);
        System.out.println("---> receive " + receive);
    }

    @Test
    void receiveAndConvert() {
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        Message message = messageConverter.toMessage(new SomeOrser("1", "2"), messageProperties);
        rabbitTemplate.send(TACOCLOUD_ORDER_QUEUE,message);

        WebTestClient.BodySpec<SomeOrser, ?> someOrserBodySpec = webTestClient.put()
                .uri("/api/orders/receiveAndConvertRabbit")
                .bodyValue(new SomeOrser("1", "2"))
                .exchange()
                .expectStatus().isCreated().expectBody(SomeOrser.class);

        SomeOrser responseBody = someOrserBodySpec.returnResult().getResponseBody();
        assert responseBody != null;
        Assertions.assertEquals("1", responseBody.getName());
        Assertions.assertEquals("2", responseBody.getSurname());
    }

    @Test
    void receiveAndConvertWithTypeReferrence() {
rabbitTemplate.setMessageConverter( new Jackson2JsonMessageConverter());
        webTestClient.put()
                .uri("/api/orders/sendToRabbit")
                .bodyValue(new SomeOrser("6", "7"))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isCreated().expectBody(String.class)
                .isEqualTo("success Send to rabbitMq");

        SomeOrser someOrser = rabbitTemplate.receiveAndConvert(TACOCLOUD_ORDER_QUEUE,
                new ParameterizedTypeReference<SomeOrser>() {
        });

        Assertions.assertEquals("6", someOrser.getName());
        Assertions.assertEquals("7", someOrser.getSurname());
    }

    @Test
    void testRabbitListener() throws InterruptedException {
        rabbitTemplate.setMessageConverter( new Jackson2JsonMessageConverter());

        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        Message message = messageConverter.toMessage(new SomeOrser("13", "33"), new MessageProperties());
        Message message1 = messageConverter.toMessage(new SomeOrser("123", "313"), new MessageProperties());
        rabbitTemplate.send(TACOCLOUD_ORDER_QUEUE, message);
        rabbitTemplate.send(TACOCLOUD_ORDER_QUEUE, message1);
        Thread.sleep(3000);

        String receive = (String) rabbitTemplate.receiveAndConvert(
                TACOCLOUD_ORDER_QUEUE, new ParameterizedTypeReference<String>() {
        });

        Assertions.assertEquals("message willbe received", receive);
    }


    //    SSLContext serverContext = SSLContext.getInstance("TLS");
//
//    final KeyStore ks = KeyStore.getInstance("JKS");
//
//ks.load(new ByteArrayInputStream(Base64Coder
//
//                                         .decode(MyKeystore.data)),
//
//            "yourkeystorepassword".toCharArray());
//
//    final KeyManagerFactory kmf = KeyManagerFactory
//
//            .getInstance(algorithm);
//
//kmf.init(ks, "yourkeystorepassword".toCharArray());
//
//serverContext.init(kmf.getKeyManagers(), null, null);
//4. Create a Netty SSLHandler from the created context:
//
//            1
//    final SslHandler sslHandler = new SslHandler(serverContext);

}
