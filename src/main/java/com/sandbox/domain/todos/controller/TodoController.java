package com.sandbox.domain.todos.controller;

import com.sandbox.domain.todos.dto.Todo;
import com.sandbox.domain.todos.service.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service){
        this.service = service;
    }

    @CrossOrigin(origins = "https://ssafysandbox.vercel.app")
    @GetMapping
    public Map<String, Object> read() {
        List<Todo> todolist =  service.read();

        //응답 구조에 맞게 json만들기
        Map<String,Object> res = new HashMap<>();
        res.put("message","정상적으로 요청되었습니다.");
        res.put("todos",todolist);
        return res;
    }


}
