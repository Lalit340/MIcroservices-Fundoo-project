package com.bridgelabz.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Email {
	private String to;
	
	private String from;
	
	private String subject;
	
	private String body;
}
