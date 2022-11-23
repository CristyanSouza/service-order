package br.com.so.ServiceOrder.services.exception;

public class InvalidCpfException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidCpfException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCpfException(String message) {
		super(message);
	}

	
	
}
