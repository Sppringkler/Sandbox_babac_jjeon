package com.sandbox.domain.todos.dao;


import com.sandbox.domain.todos.dto.TodoReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoDao extends JpaRepository<TodoReq,Integer> {
}
