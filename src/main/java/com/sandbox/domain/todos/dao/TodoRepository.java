package com.sandbox.domain.todos.dao;

import com.sandbox.domain.todos.dto.ReadTodoListResp;
import com.sandbox.domain.todos.entity.Todo;
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

    public List<Todo> getTodos() {
        return em.createQuery("select todo from Todo todo", Todo.class).getResultList();
    }

    public void createTodo(Todo todo) {
        em.persist(todo);
    }

    public void updateTodo(Todo todo) {
        todo.setCompleted(!todo.isCompleted());
        em.merge(todo);
    }

    public Todo getTodoById(int todoId) {
        return em.find(Todo.class, todoId);
    }

    public void deleteTodo(int todoId) {
        em.remove(em.find(Todo.class, todoId));
    }
}
