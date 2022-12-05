package com.anymind.pos.exception;

public class InvalidInputException extends RuntimeException {
	private String message;

	public InvalidInputException(String message) {
		super(message);
		this.message = message;
	}
}
