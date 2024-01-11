package com.example.projectJava;

import com.example.projectJava.model.Address;
import com.example.projectJava.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class ProjectJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectJavaApplication.class, args);
	}


//	@GetMapping
//	public List<User> hello(){
//		return List.of(
//				new User(
//						1L,
//						"Laurentiu",
//						"Popescu",
//						"laur@gmail.com",
//						LocalDate.of(2020, Month.APRIL, 2),
//						new Address()
//				)
//		);
//	}
}
