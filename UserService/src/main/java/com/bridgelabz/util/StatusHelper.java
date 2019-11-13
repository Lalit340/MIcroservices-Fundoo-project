package com.bridgelabz.util;

import com.bridgelabz.response.LoginResponse;
import com.bridgelabz.response.Response;

public class StatusHelper {

	public static Response statusInfo(String statusMessage, int statusCode){
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setStatusMessage(statusMessage);
		return response;
	}
	
	public static LoginResponse statusResponseInfo(String statusMessage, int statusCode,String token,String userName,String email) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setStatusMessage(statusMessage);
		loginResponse.setStatusCode(statusCode);
		loginResponse.setToken(token);
		loginResponse.setUserName(userName);
		loginResponse.setEmail(email);
		return loginResponse;
	} 
}
