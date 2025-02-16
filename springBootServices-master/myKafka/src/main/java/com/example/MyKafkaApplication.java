package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MyKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyKafkaApplication.class, args);
	}

}
