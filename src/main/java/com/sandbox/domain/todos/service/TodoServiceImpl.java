package com.sandbox.domain.todos.service;

import com.sandbox.domain.todos.repository.TodoRepository;
import com.sandbox.domain.todos.dto.*;
import com.sandbox.domain.todos.entity.Todo;

import com.sandbox.domain.todos.exception.ErrorTodoResp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository tr;

    @Override
    public ReadTodoResp getTodos() {
        List<Todo> todoList = Optional.ofNullable(tr.getTodos())
                .orElseThrow(() -> new ErrorTodoResp("읽어올 데이터가 없습니다."));

        // Todo 객체를 ReadTodoListResp 객체 매핑!
        List<ReadTodoListResp> todoListResp = todoList.stream()
                .map(todo -> new ReadTodoListResp(todo.getId(), todo.getContent(), todo.isCompleted()))
                .collect(Collectors.toList());

        return new ReadTodoResp("성공입니다", todoListResp);
    }

    @Override
    public SuccessTodoResp createTodo(CreateTodoReq req) {
        //입력형식을 todo객체로 매핑
        Todo todo = new Todo();
        todo.setContent(req.getContent());

        if(tr.createTodo(todo)<=0) throw new ErrorTodoResp("데이터가 추가되지 않았습니다.");
        return new SuccessTodoResp("데이터가 추가되었습니다.");
    }

    @Override
    @Transactional
    public SuccessTodoResp updateTodo(int todoId) {
        Todo todo = Optional.ofNullable(tr.getTodoById(todoId))
                .orElseThrow(() -> new ErrorTodoResp("수정이 실패했습니다."));

        todo.setCompleted(!todo.isCompleted());

        return new SuccessTodoResp("수정을 완료했습니다.");
    }

    @Override
    public SuccessTodoResp deleteTodo(int todoId) {
        Todo todo = Optional.ofNullable(tr.getTodoById(todoId))
            .orElseThrow(() -> new ErrorTodoResp("삭제를 실패했습니다."));

        tr.deleteTodo(todo);

        return new SuccessTodoResp("삭제를 완료했습니다.");
    }
}
