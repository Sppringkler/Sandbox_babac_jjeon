package com.sandbox.domain.todos.service;

import com.sandbox.domain.todos.dto.ReadTodoResp;
import com.sandbox.domain.todos.entity.Todo;

public interface TodoService {
    ReadTodoResp getTodos();

    void deleteTodo(int todoId);

    void updateTodo(int todoId);

    void createTodo(Todo todo);
}
