package com.mursalin.chat_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ChatAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}

}
