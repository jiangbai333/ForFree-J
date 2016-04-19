package com.sys.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseInfo {
	
	private HttpServletResponse response = null;
	
	private PrintWriter writer = null;
	
	@SuppressWarnings("unchecked")
	public ResponseInfo(HttpServletResponse response) {
		
		this.response = response;
		try {
			this.writer = this.response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public PrintWriter getWriter() {
		return writer;
	}
}