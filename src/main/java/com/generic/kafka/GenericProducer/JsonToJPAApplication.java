package com.generic.kafka.GenericProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.generic.kafka.GenericProducer.pojo")
@EnableJpaRepositories("com.generic.kafka.GenericProducer.repository")
public class JsonToJPAApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonToJPAApplication.class, args);
	}

}
