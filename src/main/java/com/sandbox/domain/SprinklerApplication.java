package com.sandbox.domain;

import com.sandbox.domain.todos.dao.TodoDao;
import com.sandbox.domain.todos.dto.Todo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SprinklerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SprinklerApplication.class, args);
	}
}
