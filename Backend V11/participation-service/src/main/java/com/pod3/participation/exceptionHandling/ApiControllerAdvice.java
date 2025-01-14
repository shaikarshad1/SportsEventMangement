package com.pod3.participation.exceptionHandling;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiControllerAdvice {
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponsePojo> handleFeignException(FeignException e)
	{
		log.error("Feign Exception Occurred {}",e.getMessage());
		//return ResponseEntity.badRequest().body(e.getMessage());
		ErrorResponsePojo errorResponse= ErrorResponsePojo.builder()
				.title("FeignException")
				.message(e.getMessage())
				.build();
		//ErrorResponsePojo errorResponse= new ErrorResponsePojo(e.getMessage(),"Feign Exception Occurred");
//				
		//return ResponseEntity.badRequest().body("Feign Exception: "+ e.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);

	}
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ErrorResponsePojo> handleMissingRequestHeaderException(MissingRequestHeaderException e)
	{
		log.error("Missing Request Header Exception Occurred {}",e.getMessage());
		ErrorResponsePojo errorResponse= ErrorResponsePojo.builder()
				.title("AuthorizationHeaderException")
				.message(e.getMessage())
				.build();
		return ResponseEntity.badRequest().body(errorResponse);
	}

}
