package com.sandbox.domain.articles.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ArticleExceptionHandler {
    @ExceptionHandler(ErrorArticleResp.class)
    public ResponseEntity<ErrorArticleResp> handleErrorTodo(ErrorArticleResp ex){
        log.error("ErrorArticleResp 발생 : {}", ex.getMessage(), ex);

        return ResponseEntity.status(202).body(new ErrorArticleResp(ex.getMessage()));
    }
}
