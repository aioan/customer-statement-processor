package com.alex.customerstatementprocessor.statement.parsers.exceptions;

public class InvalidFileStructureException extends RuntimeException {
	private static final long serialVersionUID = 8050938468609267509L;

	public InvalidFileStructureException(String message) {
		super(message);
	}

	public InvalidFileStructureException(String message, Throwable cause) {
		super(message, cause);
	}

}
