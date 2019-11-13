package com.bridgelabz.user.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginDto {
	
	@Column(name = "email",unique = true)
	@Email(regexp =  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|org))+$")
	@NotEmpty(message = "Please provide valid email")
	@NotNull(message = "Please provide valid email")
	private String userEmail;

	@NotEmpty(message = "Please provide password")
	@Column(name = "password")
	@NotNull(message = "Please provide password")
	private String password;
	
}
