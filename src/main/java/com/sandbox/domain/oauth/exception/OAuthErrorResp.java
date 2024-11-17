package com.sandbox.domain.oauth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthErrorResp extends RuntimeException {
    private int status;
    private String code;
}
