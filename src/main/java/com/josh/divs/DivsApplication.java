package com.josh.divs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DivsApplication {
	public static void main(String[] args) {
		SpringApplication.run(DivsApplication.class, args);
	}
}
