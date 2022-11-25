package br.com.so.ServiceOrder.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private List<FieldMassage> listErrors = new ArrayList<>();
	
	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FieldMassage> getListErrors() {
		return listErrors;
	}

	public void addError(String field, String message) {
		this.listErrors.add(new FieldMassage(field, message));
	}

	
	
	
}
