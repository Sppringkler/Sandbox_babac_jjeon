package com.sandbox.domain.todos.controller;

import com.sandbox.domain.todos.dto.Todo;
import com.sandbox.domain.todos.dto.TodoResp;
import com.sandbox.domain.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@CrossOrigin("https://ssafysandbox.vercel.app")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService ts;

    @GetMapping
    public TodoResp getTodos() {
        TodoResp todoResp = new TodoResp();
        todoResp.setMessage("정상적으로 요청되었습니다.");
        todoResp.setTodos(ts.getTodos());

        return todoResp;
    }

    @DeleteMapping("/{todoId}")
    public String deleteTodo(@PathVariable("todoId") int todoId) {
        ts.deleteTodo(todoId);
        return "정상적으로 처리 되었습니다";
    }

    @PatchMapping("/{todoId}")
    public String updateTodo(@PathVariable("todoId") int todoId) {
        ts.updateTodo(todoId);
        return "정상적으로 처리 되었습니다";
    }

    @PostMapping
    public String createTodo(@RequestBody Todo todo) {
        todo.setCompleted(false);
        ts.createTodo(todo);
        return "정상적으로 처리 되었습니다";
    }
}
