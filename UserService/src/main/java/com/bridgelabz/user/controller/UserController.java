package com.bridgelabz.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.response.LoginResponse;
import com.bridgelabz.response.Response;
import com.bridgelabz.user.dto.ForgotDto;
import com.bridgelabz.user.dto.LoginDto;
import com.bridgelabz.user.dto.RegisterDto;
import com.bridgelabz.user.dto.ResetDto;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.service.IUserService;

/**
 * Puspose : Controller Class for fundoo backend
 * @author Tasif Mohammed
 *
 */
@RestController
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
@RequestMapping("/user")
public class UserController {

	static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@GetMapping
	public List<User> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return allUser;
	}
	
	/**
	 * Purpose : Method for registration
	 * @param registerDto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response> register(@Valid @RequestBody RegisterDto registerDto)
	{
		logger.info("userDTO data"+registerDto.toString());
		logger.info("User Registration");
		Response statusResponse = userService.register(registerDto);
		return new ResponseEntity<Response>(statusResponse, HttpStatus.OK);
	}
	
	/**
	 * Purpose : Method for validating email
	 * @param token
	 * @return
	 */
	@GetMapping("/emailvalidation/{token}")
	public ResponseEntity<Response> validateEmail(@PathVariable String token){
		Response statusResponse = userService.validateEmail(token);
		return new ResponseEntity<Response> (statusResponse, HttpStatus.ACCEPTED);
		
	}
	
	/**
	 * Purpose : Method for Login
	 * @param loginDto
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto){
		LoginResponse statusResponse = userService.login(loginDto);
		return new ResponseEntity<LoginResponse>(statusResponse,HttpStatus.OK);
	}
	
	/**
	 * Purpose : Method for forgot password
	 * @param forgotDto
	 * @return
	 */
	@PostMapping("/forgotpassword")
	public ResponseEntity<Response> fogotPassword(@RequestBody ForgotDto forgotDto){
		Response statusResponse = userService.forgotPassword(forgotDto);
		return new ResponseEntity<Response> (statusResponse, HttpStatus.ACCEPTED);
	}
	
	/**
	 * Purpose : Method for resetting password
	 * @param resetDto
	 * @param token
	 * @return
	 */
	@PutMapping("/reset/{token}")
	public ResponseEntity<Response> resetPassword(@RequestBody ResetDto resetDto,@PathVariable String token){
		Response statusResponse = userService.resetPassword(resetDto , token);
		return new ResponseEntity<Response> (statusResponse, HttpStatus.ACCEPTED);
	}
}