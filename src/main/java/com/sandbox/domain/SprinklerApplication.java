package com.sandbox.domain;

import com.sandbox.domain.todos.dao.TodoDao;
import com.sandbox.domain.todos.dto.Todo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SprinklerApplication {

	private final TodoDao dao;

	public SprinklerApplication(TodoDao dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {

		SpringApplication.run(SprinklerApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner initializeData() {
//		return args -> {
//			if (dao.count() == 0) {  // 중복 방지: 데이터가 없을 경우에만 초기화
//				dao.save(new Todo("할 일 1", false));
//				dao.save(new Todo("할 일 2", true));
//				dao.save(new Todo("할 일 3", false));
//				dao.save(new Todo("할 일 4", true));
//				dao.save(new Todo( "할 일 5", false));
//			}
//		};
//	}

}
