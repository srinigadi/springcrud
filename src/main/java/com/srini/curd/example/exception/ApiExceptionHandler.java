package com.srini.curd.example.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(ApiRequestException.class)
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		
		//create payload containing exception in detail
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(
				e.getMessage(),
				//e,
				badRequest,
				ZonedDateTime.now(ZoneId.of("Z"))
				);
		
		// return response entity
		
		return new ResponseEntity<Object>(apiException, badRequest);
	}
	@ExceptionHandler(UserDataAccessException.class)
	public final ResponseEntity<Object> handleAllExceptions(UserDataAccessException e){
		//create payload containing exception in detail
				HttpStatus badRequest = HttpStatus.BAD_REQUEST;
				ApiException apiException = new ApiException(
						e.getMessage(),
						//e,
						badRequest,
						ZonedDateTime.now(ZoneId.of("Z"))
						);
				
				// return response entity
				
				return new ResponseEntity<Object>(apiException, badRequest);
	}

}
