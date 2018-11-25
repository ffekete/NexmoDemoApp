package com.home.nexmodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.home.nexmodemo.*")
public class NexmodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexmodemoApplication.class, args);
	}
}
