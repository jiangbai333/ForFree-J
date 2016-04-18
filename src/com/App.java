package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.common.RequestInfo;
import com.sys.common.ResponseInfo;
import com.sys.libs.Router;

@SuppressWarnings("serial")
public final class App extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public static Map info = new HashMap();
	public static ServletContext context = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	
		System.out.println("doGet");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	
		System.out.println("doPost");
	}
	
	/**
	 * 重载 service 方法, 为应用提供一个统一的入口
	 */
	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		
		/** 设置字符集编码 */
		response.setContentType("text/html; charset=UTF-8");
		
		context = this.getServletContext();
		
		/** 项目名称 */
		App.info.put("projectName", request.getContextPath().substring(1));
		App.info.put("contextPath", request.getContextPath());
		App.info.put("HeaderNames", request.getHeaderNames());
		App.info.put("Method", request.getMethod());
		App.info.put("getPathInfo", request.getPathInfo());
		App.info.put("getPathTranslated", request.getPathTranslated());
		App.info.put("getRemoteUser", request.getRemoteUser());
		App.info.put("getRequestURI", request.getRequestURI());
		App.info.put("getServletPath", request.getServletPath());
		App.info.put("getSession", request.getSession());
		App.info.put("getUserPrincipal", request.getUserPrincipal());
		App.info.put("getCharacterEncoding", request.getCharacterEncoding());
		App.info.put("getContentType", request.getContentType());
		App.info.put("getLocalAddr", request.getLocalAddr());
		App.info.put("getLocale", request.getLocale());
		App.info.put("getLocales", request.getLocales());
		App.info.put("getLocalName", request.getLocalName());
		App.info.put("getLocalPort", request.getLocalPort());
		App.info.put("getProtocol", request.getProtocol());
		App.info.put("getRemoteAddr", request.getRemoteAddr());
		App.info.put("getRemoteHost", request.getRemoteHost());
		App.info.put("getRemotePort", request.getRemotePort());
		App.info.put("getScheme", request.getScheme());
		App.info.put("getServerName", request.getServerName());
		App.info.put("getServerPort", request.getServerPort());
		
		/** 解析请求 */
		( new Router() ).run(new RequestInfo(request), new ResponseInfo(response));
			
	}
}