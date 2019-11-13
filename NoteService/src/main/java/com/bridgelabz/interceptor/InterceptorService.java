package com.bridgelabz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bridgelabz.util.JWTTokenHelper;


public class InterceptorService implements HandlerInterceptor {
	
	@Autowired
	private JWTTokenHelper jwtTokenHelper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("I am pre handler");
		String token = request.getHeader("token");
		long userId = jwtTokenHelper.decodeToken(token);
		request.setAttribute("userId", userId);
		return true;
	}

//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		System.out.println("I am post handler");
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		System.out.println("I am after completion");
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
}
