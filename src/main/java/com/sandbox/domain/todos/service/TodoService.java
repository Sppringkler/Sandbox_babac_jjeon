package com.sandbox.domain.todos.service;

import com.sandbox.domain.todos.dto.TodoResp;
import com.sandbox.domain.todos.dto.TodoReq;

public interface TodoService {
    TodoResp getTodos();

    void deleteTodo(int todoId);

    void updateTodo(int todoId);

    void createTodo(TodoReq todo);
}
