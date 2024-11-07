package com.sandbox.domain.todos.controller;

import com.sandbox.domain.todos.dto.CreateTodoReq;
import com.sandbox.domain.todos.dto.SuccessTodoResp;
import com.sandbox.domain.todos.dto.ReadTodoResp;
import com.sandbox.domain.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ReadTodoResp> getTodos() {
        ReadTodoResp resp = todoService.getTodos();
        return ResponseEntity.ok(resp);
    }

    /* 할일 추가 */
    @PostMapping
    public ResponseEntity<SuccessTodoResp> createTodo(@RequestBody CreateTodoReq req) {
        SuccessTodoResp resp = todoService.createTodo(req);
        return ResponseEntity.ok(resp);
    }

    /* 할일 수정 */
    @PatchMapping("/{todoId}")
    public ResponseEntity<SuccessTodoResp> updateTodo(@PathVariable("todoId") int todoId) {
        SuccessTodoResp resp = todoService.updateTodo(todoId);
        return ResponseEntity.ok(resp);
    }

    /* 할일 삭제 */
    @DeleteMapping("/{todoId}")
    public ResponseEntity<SuccessTodoResp> deleteTodo(@PathVariable("todoId") int todoId) {
        SuccessTodoResp resp = todoService.deleteTodo(todoId);
        return ResponseEntity.ok(resp);
    }
}
