package com.sandbox.domain.todos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadTodoListResp {
    private int id;
    private String content;
    private boolean completed;
}
