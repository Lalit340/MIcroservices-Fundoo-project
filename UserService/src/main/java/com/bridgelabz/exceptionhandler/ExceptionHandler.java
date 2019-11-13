package com.bridgelabz.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.exception.JWTTokenException;
import com.bridgelabz.exception.UserException;
import com.bridgelabz.response.Response;
import com.bridgelabz.util.StatusHelper;

@RestControllerAdvice
@PropertySource("classpath:error.properties")
public class ExceptionHandler {

	@Autowired
	Response response;
	
//	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
//	public ResponseEntity<Response> globalExceptionHandler(Exception e){
//		response = StatusHelper.statusInfo(e.getMessage(),Integer.parseInt(environment.getProperty("globalexception")));
//		return new ResponseEntity<Response> (response , HttpStatus.OK);
//	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = UserException.class)
	public ResponseEntity<Response> emailExceptionHandler(UserException e){
		response = StatusHelper.statusInfo(e.getMessage(), e.getStatusCode());
		return new ResponseEntity<Response> (response , HttpStatus.OK);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = JWTTokenException.class)
	public ResponseEntity<Response> jwtTokenExceptionHandler(JWTTokenException e){
		response = StatusHelper.statusInfo(e.getMessage(), e.getStatusCode());
		return new ResponseEntity<Response> (response , HttpStatus.OK);
	}
}