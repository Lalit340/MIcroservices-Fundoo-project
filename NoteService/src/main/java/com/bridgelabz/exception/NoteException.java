package com.bridgelabz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoteException extends RuntimeException{
	private static final long serialVersionUID = 1L;	
	private int statusCode;
	public NoteException(String message,int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
}
