package com.example.demo;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SSLServerApplication {


	public static void main(String[] args) {
		try {
			SpringApplication.run(SSLServerApplication.class, args);
		} catch (Throwable d) {
			d.printStackTrace();
		}
		}
}

