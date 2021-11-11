package com.core;

import javax.servlet.http.*;
import javax.servlet.ServletResponse;

public class Response {
	private static HttpServletResponse response;
	
	public static void set(ServletResponse res) {
		if(res instanceof HttpServletResponse) {
			Response.response = (HttpServletResponse)res;
		}
	}
	
	public static HttpServletResponse get() {
		return response;
	}
}
