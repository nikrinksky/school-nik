package ru.hogwarts.schoolnik;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@OpenAPIDefinition
public class SchoolNikApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolNikApplication.class, args);
	}

}
