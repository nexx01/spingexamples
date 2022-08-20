package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "security.enabled.customReactive=true"
})
public class securityTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void name() {
        webTestClient.get()
                .uri("/api/orders/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SomeOrder.class);
    }
}
