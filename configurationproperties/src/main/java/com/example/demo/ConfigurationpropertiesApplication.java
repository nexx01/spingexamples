package com.example.demo;

import com.example.demo.annotation.TestAnnotationClass;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
public class ConfigurationpropertiesApplication implements CommandLineRunner {

	@Autowired
	TestAnnotationClass testAnnotationClass;

	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		try {
			SpringApplication.run(ConfigurationpropertiesApplication.class, args);
		} catch (Throwable d) {
			d.printStackTrace();
		}
		}

	@Override
	public void run(String... args) throws Exception {
//		testAnnotationClass.methodForTracable();
//
//		System.out.println("After apply sort annotation" + testAnnotationClass.test().toString());
//		testAnnotationClass.withListInParametr();

		testAnnotationClass.withListInParametrMono("l").subscribe();

		//testAnnotationClass.beforeWithListInParametrMono("l").subscribe();
		//


		ResponseEntity<String> forEntity = restTemplate
				.getForEntity("https://localhost:8086/test", String.class, Collections.emptyMap());

		System.out.println(restTemplate);

		testAnnotationClass.forParametrTest("f").subscribe();
	}
}

@org.springframework.stereotype.Controller
@RequestMapping
@ConfigurationProperties(prefix = "controller.properies", ignoreInvalidFields = false, ignoreUnknownFields = false)
@Setter
@NoArgsConstructor
class Controller {
	private String first;
	private int second;
	private long third;
	private boolean fourth;

	@PostConstruct
	public void dffd(){
		System.out.println("-------------------");
		System.out.println("It is parametr first with inject value: "+ first);
	}
}