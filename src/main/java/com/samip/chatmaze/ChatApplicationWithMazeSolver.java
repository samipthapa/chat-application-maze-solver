package com.samip.chatmaze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChatApplicationWithMazeSolver {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplicationWithMazeSolver.class, args);
	}

}
