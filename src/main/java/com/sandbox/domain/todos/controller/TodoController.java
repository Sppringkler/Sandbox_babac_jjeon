package com.sandbox.domain.todos.controller;

import com.sandbox.domain.todos.dto.Todo;
import com.sandbox.domain.todos.dto.TodoResp;
import com.sandbox.domain.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin("https://ssafysandbox.vercel.app")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService ts;

    /* 리스트 전체 읽기 */
    @GetMapping
    public ResponseEntity<TodoResp> getTodos() {
        List<Todo> todoList = ts.getTodos();
        TodoResp todoResp = new TodoResp();

        if (todoList.isEmpty()) { //202로할까?
            todoResp.setMessage("데이터가 없습니다.");
        } else {
            todoResp.setMessage("정상적으로 요청되었습니다.");
        }
        todoResp.setTodos(todoList);

        return ResponseEntity.ok(todoResp);
    }

    /* 삭제 */
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") int todoId) {
        ts.deleteTodo(todoId);
        return ResponseEntity.status(200).body("정상적으로 처리 되었습니다.");
    }

    /* 수정 */
    @PatchMapping("/{todoId}")
    public ResponseEntity<String> updateTodo(@PathVariable("todoId") int todoId) {
        ts.updateTodo(todoId);
        return ResponseEntity.status(200).body("정상적으로 처리 되었습니다.");
    }

    /* 추가 */
    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody Todo todo) {
        todo.setCompleted(false);
        ts.createTodo(todo);
        return ResponseEntity.status(200).body("정상적으로 처리 되었습니다.");
    }
}
