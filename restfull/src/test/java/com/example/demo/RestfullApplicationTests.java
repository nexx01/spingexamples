package com.example.demo;

import com.example.demo.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;
import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class RestfullApplicationTests {

	@Autowired
	WebTestClient webTestClient;


//
//String url = "http://localhost:8080/api/sample/order";

	// create auth credentials
//	String authStr = "user:password";
//	String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

	// create headers
//	HttpHeaders headers = new HttpHeaders();
	//   headers.add("Authorization", "Basic " + base64Creds);
	@Test
	void contextLoads() {
		String authStr = "user:password";
		String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

		HashMap<String, String> stringStringHashMap = new HashMap<>();
		stringStringHashMap.put("sss", null);

		webTestClient.put()
				.uri("/api/sample/order")
				.header("Authorization", "Basic " + base64Creds)
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
				.bodyValue(new Order("111", "eee", 22))
				//.body(Mononew Order(), new ParameterizedTypeReference<Order>() {
				//})
				.exchange()
				.expectStatus().isOk();

	}


}
