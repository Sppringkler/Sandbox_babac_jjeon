package com.sandbox.domain.todos.service;

import com.sandbox.domain.todos.dto.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();

    void deleteTodo(int todoId);

    void updateTodo(int todoId);

    void createTodo(Todo todo);
}
