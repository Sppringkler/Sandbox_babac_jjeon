package com.sandbox.domain.todos.controller;

import com.sandbox.domain.todos.dto.Todo;
import com.sandbox.domain.todos.service.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

//@RestController : @Controller + @ResponseBody(java -> json)
//@CrossOrigin : 샌드박스 사용을 위해 설정해둔 것.
@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "https://ssafysandbox.vercel.app")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service){
        this.service = service;
    }

    @GetMapping
    public Map<String, Object> read() {
        List<Todo> todolist =  service.read();
        Map<String,Object> res = new HashMap<>();

        if(!todolist.isEmpty()) {
            res.put("message","정상적으로 요청되었습니다.");
        } else {
            res.put("message","리스트 없어요");
        }
        res.put("todos",todolist);
        return res;
    }


}
