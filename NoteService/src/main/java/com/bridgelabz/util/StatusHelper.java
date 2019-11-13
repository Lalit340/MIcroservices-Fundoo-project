package com.bridgelabz.util;

import com.bridgelabz.response.Response;

public class StatusHelper {

	public static Response statusInfo(String statusMessage, int statusCode){
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setStatusMessage(statusMessage);
		return response;
	}
	
	
}
