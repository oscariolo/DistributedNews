package com.springbootserver.distributednewsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DistributednewsserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributednewsserverApplication.class, args);
	}

}
