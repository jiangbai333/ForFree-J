package com.sys.core.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.App;
import com.sys.common.RequestInfo;
import com.sys.common.ResponseInfo;
import com.sys.common.StaticMassage;
import com.sys.libs.Template;
import com.sys.tools.Str;

@SuppressWarnings("serial")
public class ControllerBase {
	
	@SuppressWarnings("unchecked")
	public Map info = new HashMap();
	
	private PrintWriter out = null;
	
	private HttpServletRequest request = null;
	
	private HttpServletResponse response = null;
	
	protected Map<String, String> _GET = new HashMap<String, String>();
	
	protected Map<String, String> _POST = new HashMap<String, String>();
	
	public ControllerBase() {}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void init(RequestInfo res, ResponseInfo resq, String todo){

		this.out = resq.getWriter();
		this.response = resq.getResponse();
		this.request = res.getRequest();
		
		this.info.put("url", request.getScheme() + 
				"://" + request.getServerName() + 
				":" + request.getServerPort() + 
				request.getRequestURI() + 
				"?" + request.getQueryString());
		this.info.put("path", request.getRealPath(""));
		this.info.put("contextPath", request.getContextPath());
		this.info.put("localAddr", request.getLocalAddr());
		this.info.put("getServerName", request.getServerName());
				
		this._GET = res.getGet();
		this._POST = res.getPost();
		
		if ( !Str.is_invalid(todo) ) {
			try {
				this.getClass().getMethod(todo).invoke(this);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				this.error("MissingActoin", new String[] {
					"请在 app.controller." + _GET.get("p") + "." + _GET.get("c") + "Controller 中查看 " + _GET.get("a") + "()" + " 方法名是否书写正确",
					"请查看 app.controller." + _GET.get("p") + "." + _GET.get("c") + "Controller." + _GET.get("a") + "() 方法是否存在"
				});
			}
		}
	}
	
	public void error(String type, String[] msg) {
		BufferedReader ready;
		
		try {
			ready = new BufferedReader(new InputStreamReader(new FileInputStream(new File(this.info.get("path") + "/error.html")), "UTF-8"));
			String	temp = "",
					sendOut = "";
			while(null != (temp = ready.readLine())){
				sendOut += temp;
			}

			for ( String str : msg ) {
				sendOut = sendOut.replaceAll("\\{\\$msg\\}", "<span class='msg'>" + str + "</span><br><br>\\{\\$msg\\}");
			}
			
			sendOut = sendOut.replaceAll("\\{\\$msg\\}", "");
			sendOut = sendOut.replaceAll("\\{\\$errorType\\}", (String) StaticMassage.errorType.get(type));
			this.out.print(sendOut);
			this.out.flush();
			this.out.close();
			
		} catch (IOException e) {
			this.out.print((String)StaticMassage.errorType.get("missingErrorViewFile"));
			this.out.flush();
			this.out.close();
		}
	}	
	
	/**
	 * 渲染视图
	 */
	protected void display(){
		
		String file = this.info.get("path") + "/" + this._GET.get("p") + "/" + this._GET.get("a") + ".html";

		this.out.print(new Template().compile(file, this));
		this.out.flush();
		this.out.close();
	}
	
	/**
	 * 打印一个特定的字符串, 目前只为了调试程序使用
	 * @param str 将要打印的字符串
	 */
	protected void echo(String str) {
		
		this.out.print(str);
	}
	
	/**
	 * 打印 json 串儿, 目前只为了调试程序使用
	 * @param temp 将要打印的对象
	 */
	protected void print_r(Object temp) {
		
		this.out.print(JSONArray.fromObject(temp));
	}
	
	/**
	 * 打印 json 串儿并根据需要终止输出流, 目前只为了调试程序使用
	 * @param temp 将要打印的对象
	 * @param flag 是否终止输出流 true为终止输出流
	 */
	protected void print_r(Object temp, boolean flag) {
		
		this.out.print(JSONArray.fromObject(temp));
		
		if ( flag ) {
			this.out.flush();
			this.out.close();
		}
	}

	/**
	 * url请求转发
	 * @param p 请求的包
	 * @param c 请求的控制
	 * @param a 请求的控制器动作
	 */
	protected void forward(String p, String c, String a) {
		//获取ServletContext对象, 构建请求转发对象(RequestDispatcher)
		RequestDispatcher rd = 
			App.context.getRequestDispatcher("/index?p=" + p + "&c=" + c + "&a=" + a);
		
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//调用forward方法实现请求转发
	}
	
	/**
	 * 构造配置文件路径, 方便配置文件读取
	 * @param 文件名
	 * @return String 文件的绝对路径
	 */
	protected String P(String file) {
		
		return this.info.get("path") + "/WEB-INF/classes/" + file + ".properties";
	}
}