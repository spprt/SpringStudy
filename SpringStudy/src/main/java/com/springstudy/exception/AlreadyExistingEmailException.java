package com.springstudy.exception;

public class AlreadyExistingEmailException extends RuntimeException {

	private static final long serialVersionUID = -240699812090492586L;

	public AlreadyExistingEmailException(String message) {
		super(message);
	}
}
