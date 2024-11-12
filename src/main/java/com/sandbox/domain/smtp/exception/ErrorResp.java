package com.sandbox.domain.smtp.exception;

public class ErrorResp extends RuntimeException {
	public ErrorResp(String message) {
		super(message);
	}
}
