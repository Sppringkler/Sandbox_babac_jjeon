package com.sandbox.domain.todos.dao;



import com.sandbox.domain.todos.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoDao extends JpaRepository<Todo,Integer> {
}
