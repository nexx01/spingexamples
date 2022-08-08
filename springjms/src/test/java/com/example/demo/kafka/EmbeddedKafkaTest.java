//package com.example.demo.kafka;
//
//
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import javax.sound.sampled.Port;
//import java.time.Duration;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(
//        properties = {
//                "spring.kafka.consumer.auto-offset-reset = earliest",
//                "spring.kafka.consumer.group-id = baendulg",
//                "test.topic=embedded-test-topic",
//                "kafka.bootstrapAddress=localhost:9092",
//                "spring.kafka.bootstrap-servers = localhost:9092",
//"message.topic.name=baeldung"
//
//        }
//)
//@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = {
//        "listener=PLAINTEXT://localhost:9092", "port=9092"
//})
//public class EmbeddedKafkaTest {
//
//    @Autowired
//    WebTestClient client;
//
//    @Autowired
//    KafkaConsumer<String,String> kafkaConsumer;
//
//    @Autowired
//    KafkaProducer <String,String> kafkaProducer;
//
//    @Value("${test.topic}")
//    String topic;
//
//    @Test
//    void givenEmbeddedKafkaBroker_whenSendingWithSimpleProduser_thenMessageReceived() {
//
//        String data = "Sending with our own simple KafkaProducer";
//
//        kafkaProducer.send(new ProducerRecord<>(topic, data));
//
//        ConsumerRecords<String, String> poll =
//                kafkaConsumer.poll(Duration.ofSeconds(10));
//
//        System.out.println();
//
//    }
//}
