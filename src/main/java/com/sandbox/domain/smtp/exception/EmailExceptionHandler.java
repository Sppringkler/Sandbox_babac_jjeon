package com.sandbox.domain.smtp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class EmailExceptionHandler {
    @ExceptionHandler(ErrorResp.class)
    public ResponseEntity<ErrorResp> handleErrorTodo(ErrorResp ex){
        log.error("ErrorArticleResp 발생 : {}", ex.getMessage(), ex);

        return ResponseEntity.status(202).body(ex);
    }
}
