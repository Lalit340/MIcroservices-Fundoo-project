package com.bridgelabz.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.interceptor.InterceptorService;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public InterceptorService interceptorService() {
		return new InterceptorService();
	}
}
