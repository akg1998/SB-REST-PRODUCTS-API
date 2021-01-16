package com.cognizant.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.exceptions.ApiError;
import com.cognizant.exceptions.ProductNotFoundException;

@RestController
@RestControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(value=ProductNotFoundException.class)
	public ResponseEntity<ApiError>handleProductNotFoundException(){
		ApiError error =new ApiError();
		error.setErrorCode("COGNIZANT_ERR_134");
		error.setErrorMsg("Product Not Found");
		error.setDate(new Date());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
}
