package com.sandbox.domain.todos.service;

import com.sandbox.domain.todos.dao.TodoRepository;
import com.sandbox.domain.todos.dto.TodoReq;
import com.sandbox.domain.todos.dto.TodoResp;
import com.sandbox.domain.todos.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {
    private final TodoRepository tr;

    @Transactional(readOnly = true)
    @Override
    public TodoResp getTodos() {
        List<Todo> todoList = tr.getTodos();
        String message = todoList.isEmpty() ? "데이터가 없습니다." : "정상적으로 요청되었습니다.";
        return new TodoResp(message, todoList);
    }

    @Override
    public void deleteTodo(int todoId) {
        tr.deleteTodo(todoId);
    }

    @Override
    public void updateTodo(int todoId) {
        Todo todo = tr.getTodoById(todoId);
        todo.setCompleted(true);
    }

    @Override
    public void createTodo(TodoReq todo) {
        tr.createTodo(todo);
    }
}
