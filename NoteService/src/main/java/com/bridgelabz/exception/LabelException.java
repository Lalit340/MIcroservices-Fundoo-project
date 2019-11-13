package com.bridgelabz.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LabelException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;	
	private int statusCode;
	public LabelException(String message,int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
	
}
