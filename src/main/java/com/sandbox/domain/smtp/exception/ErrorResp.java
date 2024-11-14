package com.sandbox.domain.smtp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResp extends RuntimeException {
	private String message;
}
