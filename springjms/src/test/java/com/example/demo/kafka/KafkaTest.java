package com.example.demo.kafka;

import com.example.demo.SomeOrser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers = localhost:29092",
        "spring.kafka.template.default-topic = tacocloud.orders.topic",
//        "kafka.producer.key.serializer=org.apache.kafka.common.serialization.StringSerializer",
//        "kafka.producer.value.serialize=org.apache.kafka.common.serialization.ByteArraySerializer",
//        "key.serializer=org.apache.kafka.common.serialization.StringSerializer",
//        "value.serializer=org.apache.kafka.common.serialization.ByteArraySerializer"
//        "key.serializer=org.springframework.kafka.support.serializer.JsonSerializer",
//        "value.serializer=org.springframework.kafka.support.serializer.JsonSerializer",
        "spring.kafka.producer.keyserializer=org.springframework.kafka.support.serializer.JsonSerializer",
        "spring.kafka.producer.valueserializer=org.springframework.kafka.support.serializer.JsonSerializer",
        "spring.kafka.consumer.group-id=test-consumer-group",
        ""
})
//@Testcontainers
public class KafkaTest {

    @Autowired
    WebTestClient client;
//
//    @Container
//    KafkaContainer kafka = new KafkaContainer(
//            DockerImageName.parse("alpine")
//                    .asCompatibleSubstituteFor("confluentinc/cp-kafka"));
////            .withNetwork(network)
////            .withExternalZookeeper("zookeeper:2181"); //todo container not work

    @Test
    void name() {
        client.post()
                .uri("/api/inKafka/orders/sendDefault")
                .bodyValue(new SomeOrser("1", "2"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("sendDefault method");
    }

    @Test
    void simpleSend() throws InterruptedException {
        client.post()
                .uri("/api/inKafka/orders/send")
                .bodyValue(new SomeOrser("1", "3"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("simpleSend");

        Thread.sleep(11111);
    }

}
