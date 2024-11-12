package com.sandbox.domain.smtp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SMTPExceptionHandler {

    @ExceptionHandler(ErrorResp.class)
    public ResponseEntity<ErrorResp> handleErrorResp(ErrorResp ex) {
        return ResponseEntity.status(202).body(new ErrorResp(ex.getMessage()));
    }
}
