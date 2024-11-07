package com.sandbox.domain.todos.controller;

import com.sandbox.domain.todos.dto.TodoReq;
import com.sandbox.domain.todos.dto.TodoResp;
import com.sandbox.domain.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@CrossOrigin("https://ssafysandbox.vercel.app")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /* 리스트 전체 읽기 */
    @GetMapping
    public ResponseEntity<TodoResp> getTodos() {
        TodoResp resp = todoService.getTodos();
        if (resp.getTodos().isEmpty()) {
            // 데이터가 없으면 202 상태 코드로 반환하기
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
        }
        return ResponseEntity.ok(resp);
    }

    /* 할일 추가 */
    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody TodoReq todo) {
        todoService.createTodo(todo);
        return ResponseEntity.ok("정상적으로 처리되었습니다.");
    }

    /* 할일 수정 */
    @PatchMapping("/{todoId}")
    public ResponseEntity<String> updateTodo(@PathVariable("todoId") int todoId) {
        todoService.updateTodo(todoId);
        return ResponseEntity.ok("정상적으로 처리되었습니다.");
    }

    /* 할일 삭제 */
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") int todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("정상적으로 처리되었습니다.");
    }
}
