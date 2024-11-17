package com.sandbox.domain.oauth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class OAuthExceptionHandler {
    @ExceptionHandler(OAuthErrorResp.class)
    public ResponseEntity<OAuthErrorResp> handleOAuthException(OAuthErrorResp ex){
        return ResponseEntity.status(ex.getStatus()).body(ex);
    }
}
