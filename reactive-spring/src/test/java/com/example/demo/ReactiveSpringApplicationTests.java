package com.example.demo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.reactive.server.JsonPathAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReactiveSpringApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Test
	void contextLoads() {
		webTestClient.get()
				.uri("/hello")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.isEqualTo("Hello World!");
	}

	@Test
	void sayBuy() {
		webTestClient.get()
				.uri("/buy")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.isEqualTo("See ya!");
	}

	@Autowired
	OrderRepo orderRepo;
	@Test
	void recent() {

		SomeOrder[] tacos = {
				testTaco(1L), testTaco(2L),
				testTaco(3L), testTaco(4L),
				testTaco(5L), testTaco(6L),
				testTaco(7L), testTaco(8L),
				testTaco(9L), testTaco(10L),
				testTaco(11L), testTaco(12L),
				testTaco(13L), testTaco(14L),
				testTaco(15L), testTaco(16L)};
		Flux<SomeOrder> tacoFlux = Flux.just(tacos);

		OrderRepo tacoRepo = Mockito.mock(OrderRepo.class);
		Mockito.when(tacoRepo.findAll()).thenReturn(tacoFlux);

		webTestClient.get()
//				uriBuilder -> uriBuilder."/api/orders")
		.uri(uriBuilder ->
				uriBuilder
						.path("/api/orders")
						.queryParam("recent")
						.build())
				.exchange()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$").isNotEmpty()
				.jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
				.jsonPath("$[0].name").isEqualTo("1")
				.jsonPath("$[1].id").isEqualTo(tacos[1].getId().toString())
				.jsonPath("$[1].name").isEqualTo("2")
				.jsonPath("$[11].id").isEqualTo(tacos[11].getId().toString())
				.jsonPath("$[11].name").isEqualTo("12")
				.jsonPath("$[12]").doesNotExist();
	}

	@Test
	void expectBodyJsonFile() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("recent-order.json");
		String recentJson = StreamUtils.copyToString(
				classPathResource.getInputStream(), Charset.defaultCharset());
		var recent = webTestClient.get().uri(uriBuilder ->
						uriBuilder.path("/api/orders").queryParam("recent").build())
				.exchange()
				.expectStatus().isOk()
				//.returnResult(String.class).getResponseBody()
				//.subscribe(s-> System.out.println(s));
	.expectBody().json(recentJson);
	}

	private SomeOrder testTaco(Long number) {
		SomeOrder taco = new SomeOrder();
		taco.setId(number != null ? number.toString(): "TESTID");
		taco.setName( number.toString());
		return taco;
	}
}
