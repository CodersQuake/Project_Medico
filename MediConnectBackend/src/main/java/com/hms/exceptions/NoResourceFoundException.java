package com.hms.exceptions;

public class NoResourceFoundException extends RuntimeException{
	public NoResourceFoundException(String message) {
		super(message);
	}
}
