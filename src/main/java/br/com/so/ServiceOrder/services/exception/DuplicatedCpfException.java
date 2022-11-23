package br.com.so.ServiceOrder.services.exception;

public class DuplicatedCpfException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DuplicatedCpfException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedCpfException(String message) {
		super(message);
	}

	
	
}
