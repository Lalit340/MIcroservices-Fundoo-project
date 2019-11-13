package com.bridgelabz.user.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.exception.UserException;
import com.bridgelabz.response.LoginResponse;
import com.bridgelabz.response.Response;
import com.bridgelabz.user.dto.ForgotDto;
import com.bridgelabz.user.dto.LoginDto;
import com.bridgelabz.user.dto.RegisterDto;
import com.bridgelabz.user.dto.ResetDto;
import com.bridgelabz.user.model.Email;
import com.bridgelabz.user.model.User;
import com.bridgelabz.user.repository.UserRespository;
import com.bridgelabz.util.JWTTokenHelper;
import com.bridgelabz.util.Producer;
import com.bridgelabz.util.StatusHelper;

/**
 * Purpose : Method to register
 * @author Tasif Mohammed
 */
@Service("userService")
@PropertySource("classpath:error.properties")
@PropertySource("classpath:message.properties")
public class UserServiceImpl implements IUserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRespository userRepository; 
	
	@Autowired
    private Environment environment;
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Override
	public List<User> getAllUser() {
		List<User> allUser = userRepository.findAll();
		return allUser;
	}
	
	/* (non-Javadoc)
	 * @see com.bridgelabz.user.service.IUserService#register(com.bridgelabz.user.dto.RegisterDto)
	 */
	@Override
	@Transactional
	public Response register(RegisterDto registerDto) {
		Email email = new Email();	
		Response response = new Response();
		logger.info((registerDto.toString()));
		Optional<User> userAvailability = userRepository.findByUserEmail(registerDto.getUserEmail());
		if(userAvailability.isPresent()) {
			logger.error("User already exist {}",userAvailability.get());
			throw new UserException(environment.getProperty("userExceptionMessage"), Integer.parseInt(environment.getProperty("userExceptionCode")));
		}
		registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		User user = modelMapper.map(registerDto, User.class);
		user.setRegisteredDate(LocalDate.now());
		userRepository.save(user);
		String emailBody = "http://localhost:8081/user/emailvalidation/" + jwtTokenHelper.generateToken(user.getUserId());
		email.setBody(emailBody);
		email.setTo(registerDto.getUserEmail());
		email.setSubject("Email validation");
		email.setFrom("${EmailId}");
		producer.send(email);
		logger.info("User registered successfully {}", user);
		response = StatusHelper.statusInfo(environment.getProperty("registerSuccess"), Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.user.service.IUserService#validateEmail(java.lang.String)
	 */
	@Override
	@Transactional
	public Response validateEmail(String token) {
		Response response = new Response();
		long userId = jwtTokenHelper.decodeToken(token);
		Optional<User> user = userRepository.findById(userId);
		user.get().setVarified(true);
		user.get().setUpdatedDate(LocalDate.now());
		userRepository.save(user.get());
		logger.info("User id is successfully verified {}", user.get());
		response = StatusHelper.statusInfo(environment.getProperty("emailVarification"), Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.user.service.IUserService#login(com.bridgelabz.user.dto.LoginDto)
	 */
	@Override
	public LoginResponse login(LoginDto loginDto) {
		LoginResponse loginResponse  = new LoginResponse();
		Optional<User> userAvailability = userRepository.findByUserEmail(loginDto.getUserEmail());
		if(!userAvailability.isPresent()) {
			logger.error("User does not exist {}", userAvailability.get());
			throw new UserException(environment.getProperty("userExceptionMessage"), Integer.parseInt(environment.getProperty("userExceptionCode")));
		}
		
		if(userAvailability.get().isVarified() != true) {
			logger.error("User id is not verified {}", userAvailability.get());
			throw new UserException(environment.getProperty("emailNotVerified"), Integer.parseInt(environment.getProperty("userExceptionCode")));
		}
		
		if(passwordEncoder.matches(loginDto.getPassword(), userAvailability.get().getPassword())) {
			String token = jwtTokenHelper.generateToken(userAvailability.get().getUserId());
			String userName = userAvailability.get().getUserName();
			String email = userAvailability.get().getUserEmail();
			logger.info("User successfully logged in {}", userAvailability.get());
			loginResponse = StatusHelper.statusResponseInfo(environment.getProperty("loginSucces"), Integer.parseInt(environment.getProperty("successCode")), token, userName, email);
			return loginResponse;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.user.service.IUserService#forgotPassword(com.bridgelabz.user.dto.ForgotDto)
	 */
	@Override
	public Response forgotPassword(ForgotDto forgotDto) {
		Response response = new Response();
		Email email = new Email();	
		Optional<User> userAvailability = userRepository.findByUserEmail(forgotDto.getUserEmail());
		if(!userAvailability.isPresent()) {
			logger.error("User does not exist {}", userAvailability.get());
			throw new UserException(environment.getProperty("userExceptionMessage"), Integer.parseInt(environment.getProperty("userExceptionCode")));
		}
		String token = jwtTokenHelper.generateToken(userAvailability.get().getUserId());
		String  emailBody ="http://localhost:8081/user/reset/"+token;
		email.setBody(emailBody);
		email.setSubject("Forgot Password");
		email.setTo(forgotDto.getUserEmail());
		email.setFrom("${EmailId}");
		producer.send(email);
		logger.info("Link for forgot password has been sent {}", userAvailability.get());
		response = StatusHelper.statusInfo(environment.getProperty("forgotPassword"), Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

	/* (non-Javadoc)
	 * @see com.bridgelabz.user.service.IUserService#resetPassword(com.bridgelabz.user.dto.ResetDto, java.lang.String)
	 */
	@Override
	@Transactional
	public Response resetPassword(ResetDto resetDto,String token) {
		Response response = new Response();
		long userId = jwtTokenHelper.decodeToken(token);
		Optional<User> userAvailability = userRepository.findById(userId);
		if(!userAvailability.isPresent()) {
			logger.error("User does not exist {}", userAvailability.get());
			throw new UserException(environment.getProperty("userExceptionMessage"), Integer.parseInt(environment.getProperty("userExceptionCode")));
		}
		if(!resetDto.getNewPassword().equals(resetDto.getConformPassword())) {
			logger.error("Password doesn't match {}", userAvailability.get());
			throw new UserException(environment.getProperty("passwordError"), Integer.parseInt(environment.getProperty("userExceptionCode")));
		}
		userAvailability.get().setPassword(passwordEncoder.encode(resetDto.getConformPassword()));
		userAvailability.get().setUpdatedDate(LocalDate.now());
		userRepository.save(userAvailability.get());
		logger.info("Password reset successfully {}", userAvailability.get());
		response = StatusHelper.statusInfo(environment.getProperty("resetPassword"), Integer.parseInt(environment.getProperty("successCode")));
		return response;
	}

}