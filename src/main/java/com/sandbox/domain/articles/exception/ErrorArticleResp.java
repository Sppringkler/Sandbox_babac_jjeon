package com.sandbox.domain.articles.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorArticleResp extends RuntimeException{
    private String msg;
}
