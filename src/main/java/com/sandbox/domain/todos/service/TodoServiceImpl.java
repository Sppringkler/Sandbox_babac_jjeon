package com.sandbox.domain.todos.service;

import com.sandbox.domain.todos.dao.TodoDao;
import com.sandbox.domain.todos.dto.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements  TodoService{

    private final TodoDao dao;

    public TodoServiceImpl(TodoDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Todo> read() {
        return dao.findAll();
    }
}
