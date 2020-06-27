package com.alex.customerstatementprocessor.statement.parsers.exceptions;

public class UnsupportedFileTypeException extends RuntimeException {
	private static final long serialVersionUID = -8371044408357004990L;

	public UnsupportedFileTypeException(String message) {
		super(message);
	}

}
