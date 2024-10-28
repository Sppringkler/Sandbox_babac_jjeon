package com.sandbox.domain.todos.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Todo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //자동증가
    private int id;

    @Column(length = 50, nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean completed;

    public Todo(){
        this.completed = false;
    }

    public Todo(String content, boolean completed) {
        this.content = content;
        this.completed = completed;
    }


    public Todo(int id, String content, boolean completed) {
        this.id = id;
        this.content = content;
        this.completed = completed;
    }
}
