package com.sandbox.domain.todos.service;

import com.sandbox.domain.todos.dao.TodoRepository;
import com.sandbox.domain.todos.dto.ErrorTodoResp;
import com.sandbox.domain.todos.dto.ReadTodoListResp;
import com.sandbox.domain.todos.dto.ReadTodoResp;
import com.sandbox.domain.todos.entity.Todo;
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
    public void deleteTodo(int todoId) {
        tr.deleteTodo(todoId);
    }

    @Override
    public void updateTodo(int todoId) {
        Todo todo = tr.getTodoById(todoId);

        tr.updateTodo(todo);
    }

    @Override
    public void createTodo(Todo todo) {
        tr.createTodo(todo);
    }
}
