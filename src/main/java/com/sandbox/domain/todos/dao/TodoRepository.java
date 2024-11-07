package com.sandbox.domain.todos.dao;

import com.sandbox.domain.todos.entity.Todo;
import com.sandbox.domain.todos.dto.TodoReq;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class TodoRepository {

    @PersistenceContext
    private final EntityManager em;

    public void createTodo(Todo todo) {
        em.persist(todo);
    }

    public List<Todo> getTodos() {
        return em.createQuery("select todo from Todo todo", Todo.class).getResultList();
    }

    public Todo getTodoById(int todoId) {
        return em.find(Todo.class, todoId);
    }

    public void deleteTodo(int todoId) {
        em.remove(em.find(Todo.class, todoId));
    }
}
