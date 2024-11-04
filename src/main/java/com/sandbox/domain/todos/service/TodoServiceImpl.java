package com.sandbox.domain.todos.service;

import com.sandbox.domain.todos.dao.TodoRepository;
import com.sandbox.domain.todos.dto.Todo;
import com.sandbox.domain.todos.dto.TodoResp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository tr;

    @Override
    public List<Todo> getTodos() {
        return tr.getTodos();
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
