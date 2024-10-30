package com.sandbox.domain.todos.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    int id;

    String content;
    boolean completed;

    /*
     * 미리 테스트 데이터 넣어둘래!!
     * */
    public Todo(String content, boolean completed) {
        this.content = content;
        this.completed = completed;
    }
}
