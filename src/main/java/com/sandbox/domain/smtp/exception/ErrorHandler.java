package com.sandbox.domain.smtp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(ErrorResp.class)
    public ResponseEntity<ErrorResp> handleErrorResp(ErrorResp ex) {
        log.error("Error 발생: {}", ex.getMessage());
        ErrorResp errorResp = new ErrorResp(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResp);
    }
}
