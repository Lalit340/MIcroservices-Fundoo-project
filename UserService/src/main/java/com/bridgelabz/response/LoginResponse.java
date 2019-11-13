package com.bridgelabz.response;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginResponse {
	private String statusMessage;	
	private int statusCode;
	private String token;
	private String userName;
	private String email;
}
