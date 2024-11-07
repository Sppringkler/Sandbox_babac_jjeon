package com.sandbox.domain.todos.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class TodoExceptionHandler {

    @ExceptionHandler(ErrorTodoResp.class)
    public ResponseEntity<ErrorTodoResp> handleErrorTodo(ErrorTodoResp ex){
        log.error("ErrorTodoResp 발생 : {}", ex.getMessage(), ex);

        return ResponseEntity.status(202).body(new ErrorTodoResp(ex.getMessage()));
    }
}
