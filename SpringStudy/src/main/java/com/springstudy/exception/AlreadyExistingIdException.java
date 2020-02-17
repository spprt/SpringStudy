package com.springstudy.exception;

public class AlreadyExistingIdException extends RuntimeException {

	private static final long serialVersionUID = 1511116428623246847L;

	public AlreadyExistingIdException(String message) {
		super(message);
	}
}
