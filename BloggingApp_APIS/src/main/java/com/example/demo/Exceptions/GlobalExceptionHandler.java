package com.example.demo.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.Entity.ApiResponse;
import com.example.demo.Payloads.UserDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		
		ApiResponse apiResponse= new ApiResponse(message ,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String,String> resp= new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
			        String fieldName = ((FieldError)error).getField();
			        String defaultMessage = error.getDefaultMessage();
			        resp.put(fieldName, defaultMessage);
		}
		);
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ApiResponse> handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex){
		
		
		ApiResponse apiResponse= new ApiResponse( "user is not found for this id",false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse> handlerBadCredentialsException(BadCredentialsException ex){
		
		
		ApiResponse apiResponse= new ApiResponse( "Invalid username and password",false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
}
