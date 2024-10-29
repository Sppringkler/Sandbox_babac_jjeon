package com.sandbox.domain.todos.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TodoResp {
    String message;
    List<Todo> todos;
}
