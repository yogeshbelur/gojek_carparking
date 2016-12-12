package com.gojek.parkinglot.exceptions;

public class NoCarFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoCarFoundException(String message) {
		super(message);
	}
}
