package com.bridgelabz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTTokenException extends RuntimeException{
	private static final long serialVersionUID = 1L;	
	private int statusCode;
	public JWTTokenException(String message,int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
}
