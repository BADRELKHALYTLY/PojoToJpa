package com.generic.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.generic.jpa.pojo.Department;
import com.generic.jpa.repository.DepatmentRepository;

@SpringBootApplication
@EntityScan("com.generic.jpa.pojo")
@EnableJpaRepositories("com.generic.jpa.repository")
public class JsonToJPAApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonToJPAApplication.class, args);
	}


	@Bean
	CommandLineRunner start(DepatmentRepository depatmentRepository){
		return args -> {
			Department department  = new Department( );
			department.setDepartmentName("salesforce");
			department.setDepartmentNumber(2);
			depatmentRepository.save(department);
			Department department1  = new Department( );
			department1.setDepartmentName("B2C");
			department1.setDepartmentNumber(3);
			depatmentRepository.save(department1);
		};
	}
}
