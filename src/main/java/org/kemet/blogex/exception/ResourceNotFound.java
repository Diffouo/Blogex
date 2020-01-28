package org.kemet.blogex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String service;
	private String field;
	private int value;
	private String stringVal;
	
	public ResourceNotFound(String service, String field, int value) {
		super(String.format("Impossible de trouver la resource %s dont %s = '%d'", service, field, value));
		this.service = service;
		this.field = field;
		this.value = value;
	}

	public ResourceNotFound(String stringRes) {
		super(String.format("La resource %s n\'a aucun droit sur cette donnée", stringRes));
	}
	

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getStringVal() {
		return stringVal;
	}

	public void setStringVal(String stringVal) {
		this.stringVal = stringVal;
	}
	
	

}
