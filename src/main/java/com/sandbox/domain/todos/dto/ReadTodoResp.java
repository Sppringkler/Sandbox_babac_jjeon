package com.sandbox.domain.todos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadTodoResp {
    private String message;
    private List<ReadTodoListResp> todos;
}
