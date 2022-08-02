package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
//@TestConfiguration(Initilazer.class)
@AutoConfigureWebClient
public class SpringjmsApplicationTest {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Destination destination;
    //  static GenericContainer<?> keycloak =
    //    new GenericContainer<>(DockerImageName.parse("jboss/keycloak:11.0.0"));
    //
    //  static {
    //    keycloak.start();
    //  }

    @BeforeEach
    public void setUp() {
        client = client.mutate()
                .responseTimeout(Duration.ofMinutes(30))
                .build();
    }
    @Autowired
    WebTestClient client;
    @Container
    public static DockerComposeContainer enviroment = new DockerComposeContainer(
            new File("C:\\Users\\V\\IdeaProjects\\spingexamples\\springjms\\src\\test\\resources\\docker-compose" +
                    ".yaml"))
            .withExposedService("activemq", 61616, Wait.forListeningPort());

//    @DynamicPropertySource
//    static void postgresqlProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
//        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
//    }

    @Test
    void test() {
        SomeOrser someOrser = new SomeOrser();
        someOrser.name="1111111";
        someOrser.surname="222222222";

        System.out.println();
        client
                .post()
                .uri("/api/orders")
                .syncBody(someOrser)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectBody(String.class)
                .isEqualTo("repo.save(order);");
    }

    @Test
    void testWithReceiveMessage() {
        SomeOrser someOrser = new SomeOrser();
        someOrser.name="1111111";
        someOrser.surname="222222222";

        client
                .post()
                .uri("/api/orders")
                .syncBody(someOrser)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectBody(String.class)
                .isEqualTo("repo.save(order);");
        System.out.println(destination);
        System.out.println("--->"+jmsTemplate.receive(destination));
    }


    @Test
    void testConvertAndSendJms() {
        SomeOrser someOrser = new SomeOrser("33333", "444444");

        client.post()
                .uri("api/orders/convertAndSendJms")
                .syncBody(someOrser)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectBody(String.class)
                .isEqualTo("repo.save(order);");

        System.out.println("--->"+jmsTemplate.receive(destination));
    }

    @Test
    void sendWithAdditionalMessage() throws JMSException {
        SomeOrser someOrser = new SomeOrser("33333", "444444");

        client.post()
                .uri("api/orders/sendWithAdditionalMessage")
                .syncBody(someOrser)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectBody(String.class)
                .isEqualTo("repo.save(order);");

        Message receive = jmsTemplate.receive(destination);
        assertEquals(receive.getStringProperty("X_ORDER_SOURCE"),"WEB");
        System.out.println("--->"+ receive);
    }

    @Test
    void convertandsendWithAdditionalMessage() throws JMSException {
        SomeOrser someOrser = new SomeOrser("33333", "444444");

        client.post()
                .uri("api/orders/convertandsendWithAdditionalMessage")
                .syncBody(someOrser)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectBody(String.class)
                .isEqualTo("repo.save(order);");

        Message receive = jmsTemplate.receive(destination);
        assertEquals(receive.getStringProperty("X_ORDER_SOURCE"),"WEB");
        System.out.println("--->"+ receive);
    }


    @Test
    void convertAndSendJmsNotSerializableEntity() {
        OrderNotSerializable orderNotSerializable = new OrderNotSerializable("5555", "66666");

        client.post()
                .uri("api/orders/convertAndSendJmsNotSerializableEntity")
                .syncBody(OrderNotSerializable.class)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectBody(String.class)
                .isEqualTo("convertAndSendJmsNotSerializableEntity");

        System.out.println("message receved: ---->" + jmsTemplate.receive(destination));
    }
}
