package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.common.RequestInfo;
import com.sys.common.ResponseInfo;
import com.sys.libs.Router;

@SuppressWarnings("serial")
public final class App extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	System.out.println("doGet");
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	System.out.println("doPost");
    }
    
    public void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    	
    	response.setContentType("text/html; charset=UTF-8");

    	( new Router() ).run(new RequestInfo(request), new ResponseInfo(response));
    	
	}
}