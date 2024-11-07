package com.sandbox.domain.todos.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorTodoResp extends RuntimeException {
    private String message;
}
