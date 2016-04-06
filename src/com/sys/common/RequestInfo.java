package com.sys.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sys.tools.Str;

public class RequestInfo {
	
	public HttpServletRequest request = null;
	
	public String projectName = null;
		
	private Map<String, String> post = new HashMap<String, String>();
	
	private Map<String, String> get = new HashMap<String, String>();
	
	@SuppressWarnings("unchecked")
	public RequestInfo(HttpServletRequest request) {
		
		this.request = request;
		
		this.init();
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		
		String queryStr = request.getQueryString();
		
		if ( !Str.is_invalid(queryStr) ) {

			String[] temp = queryStr.split("&");
			
			for ( int i = 0; i < temp.length; i++ ) {
				
				String key = temp[i].split("=")[0];
				String value = temp[i].split("=")[1];

				this.get.put(key, value);
			}
		}
		
		Enumeration pName = request.getParameterNames();
		
    	while(pName.hasMoreElements()){
    		
    		String param = (String) pName.nextElement();
    		
    		if ( !this.get.containsKey(param) ) {
    			
    			String key = param;
    			String value = this.request.getParameterValues(param)[0];
    			this.post.put(key, value);
    		}
    	}
    	
    	this.projectName = request.getContextPath().substring(1);
	}

	public Map<String, String> getPost() {
		
		return post;
	}

	public Map<String, String> getGet() {
		
		return get;
	}

	public String _GET(String key) {
		
		if ( this.get.containsKey(key) ) {
			
			return this.get.get(key);
		} else {
			
			return null;
		}
	}

	public String _setCoreQueryString(String key, String value) {
		
		if ( key.equals("c") || key.equals("a") || key.equals("p") ) {
			
			this.get.put(key, value);
			return value;
		} else {
			
			return null;
		}
	}

	public String _POST(String key) {
		
		if ( this.post.containsKey(key) ) {
			
			return this.post.get(key);
		} else {
			
			return null;
		}
	}
}