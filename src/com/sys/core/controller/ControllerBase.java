package com.sys.core.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sys.common.RequestInfo;
import com.sys.common.ResponseInfo;
import com.sys.tools.Str;
@SuppressWarnings("serial")
public class ControllerBase {
	
	public String projectName = null;
	
	public PrintWriter out = null;
	
	public Map<String, String> _GET = new HashMap<String, String>();
	
	public Map<String, String> _POST = new HashMap<String, String>();
	
	public ControllerBase() {}
	
	@SuppressWarnings("unused")
	private Map<String, String> errorMsg = new HashMap<String, String>(){{
		put("def_action", "缺失控制器动作");
	}};
	
	public void init(RequestInfo res, ResponseInfo resq, String todo){
		
		this.out = resq.writer;
		
		this.projectName = res.projectName;
		
		this._GET = res.getGet();
		this._POST = res.getPost();
		
		if ( !Str.is_invalid(todo) ) {
			try {
				this.getClass().getMethod(todo).invoke(this);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				try {
					this.error( new String[] {
						"asdasda",
						"<br>请查看 app.controller." + _GET.get("p") + "." + _GET.get("c") + "Controller." + _GET.get("a") + "() 方法是否存在"
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void error(String[] msg) throws IOException {
		BufferedReader ready;
		
		try {
			ready = new BufferedReader(new InputStreamReader(new FileInputStream(new File("../webapps/" + this.projectName + "/error.html")), "UTF-8"));
			String	temp = "",
					sendOut = "",
					tempStr = "";
			while(null != (temp = ready.readLine())){
				sendOut += temp;
			}
						
			for ( String str : msg ) {
				tempStr += ( str + "<br>" );
			}
			
			this.out.print(sendOut.replaceAll("\\{\\$msg\\}", tempStr));
			this.out.flush();
			this.out.close();
			
		} catch (IOException e) {

	    	System.out.println("渲染失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void display(){
		BufferedReader ready;
		
		try {
			ready = new BufferedReader(new InputStreamReader(new FileInputStream(new File("../webapps/" + this.projectName + "/" + this._GET.get("p") + "/" + this._GET.get("a") + ".html")), "UTF-8"));
			String	temp = "",
					str = "";
			while(null != (temp = ready.readLine())){
				 str += temp;
			}
			
			Class<?> cls= this.getClass();
			
			Pattern p = Pattern.compile("\\{\\$(.+?)\\}");
	        Matcher m = p.matcher(str);
	        ArrayList<String> strs = new ArrayList<String>();
	        while (m.find()) {
	            strs.add(m.group(1));            
	        } 
	        for (String s : strs){
	            try {
	            	
	            	str = str.replaceAll("\\{\\$" + s + "\\}", (String) cls.getField(s).get(this));
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}////这个就是属性的值
	        }
	        
			this.out.print(str);
			this.out.flush();
			this.out.close();
			
		} catch (IOException e) {

	    	System.out.println("渲染失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write() {

		this.out.print("缺少错误信息描述文件，这不是并不是一个严重错误，当你控制器以及控制器动作正确时，不会影响应用的正常运行");
		this.out.flush();
		this.out.close();
	}
	
}