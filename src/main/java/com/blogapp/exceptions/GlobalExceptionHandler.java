package com.blogapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		ApiResponse apiResponse = new ApiResponse(false, ex.getMessage());
		
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> map = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(error -> {
			
			String fieldName = ((FieldError)error).getField();
			String message = ((FieldError)error).getDefaultMessage();
			map.put(fieldName, message);
		});
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
		
		ApiResponse apiResponse = new ApiResponse(true, ex.getMessage());
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

}
