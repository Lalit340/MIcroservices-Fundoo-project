package com.bridgelabz.user.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
	
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@Column(name = "name")
	@NotEmpty(message = "please provide your name")
	@NotNull(message = "please provide your name")
	private String userName;
	
	@Column(name = "email",unique = true)
	@Email(regexp =  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|org))+$")
	@NotEmpty(message = "Please provide valid email")
	@NotNull(message = "Please provide valid email")
	private String userEmail;

	@NotEmpty(message = "Please provide password")
	@Column(name = "password")
	@NotNull(message = "Please provide password")
	private String password;
	
	@Column(name = "mobileNumber")
	@Pattern(regexp = "[0-9]{10}" , message = "provide valid mobile number")
	@NotEmpty(message = "please provide your mobile number")
	@NotNull(message = "please provide your mobile number")
	private String userMobileNumber;
	
	@Column(name = "isVerified")
	boolean isVarified;
	
	@Column(name = "registeredDate")
	private LocalDate registeredDate;

	@Column(name = "updatedDate")
	private LocalDate updatedDate;
}
