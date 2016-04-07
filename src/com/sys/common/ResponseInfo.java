package com.sys.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseInfo {
	
	public HttpServletResponse response = null;
	
	public PrintWriter writer = null;
	
	@SuppressWarnings("unchecked")
	public ResponseInfo(HttpServletResponse response) {
		
		this.response = response;
		this.init();
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		
		try {
			this.writer = this.response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}