package com.example.demo;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.Buffer;

@SpringBootApplication
public class ClientRsocketApplication {


	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ClientRsocketApplication.class, args);

		RSocket socket =
				RSocketFactory.connect()
						.transport(TcpClientTransport.create("localhost", 7000))
						.start()
						.block();

		socket
				.fireAndForget(DefaultPayload.create("Client service is available now"))
				.subscribe(f->{

				});



		Thread.sleep(3000);
		socket.dispose();

	}

}