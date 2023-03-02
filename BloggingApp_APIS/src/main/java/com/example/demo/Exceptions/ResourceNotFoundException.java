package com.example.demo.Exceptions;

import javax.management.RuntimeErrorException;

public class ResourceNotFoundException extends RuntimeException{

	
	String resouceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resouceName, String fieldName, long fieldValue) {
		super( String.format("%s not found with %s: %1", resouceName,fieldName,fieldValue));
		this.resouceName = resouceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
