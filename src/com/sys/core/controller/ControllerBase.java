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

import com.App;
import com.sys.common.RequestInfo;
import com.sys.common.ResponseInfo;
import com.sys.libs.Template;
import com.sys.tools.Str;
@SuppressWarnings("serial")
public class ControllerBase {
	
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
			ready = new BufferedReader(new InputStreamReader(new FileInputStream(new File("../webapps/" + App.info.get("projectName") + "/error.html")), "UTF-8"));
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
			this.missingErrorViewFile();
		}
	}	
	
	public void display(){
		
		String file = "../webapps/" + App.info.get("projectName") + "/" + this._GET.get("p") + "/" + this._GET.get("a") + ".html";

		this.out.print(new Template().compile(file, this));
		this.out.flush();
		this.out.close();
	}
	
	public void missingErrorViewFile() {

		this.out.print("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>文件缺失</title><style type='text/css'>*{ padding: 0; margin: 0; }body{ background: #fff; font-family: '微软雅黑'; color: #333; font-size: 16px; }.system-message{ padding: 24px 48px; }.system-message h1{ font-size: 100px; font-weight: normal; line-height: 120px; margin-bottom: 12px; }.system-message .jump{ padding-top: 10px}.system-message .jump a{ color: #333;}.system-message .success,.system-message .error{ line-height: 1.8em; font-size: 36px }.system-message .detail{ font-size: 12px; line-height: 20px; margin-top: 12px; display:none}</style></head><body><div class='system-message'><h1>T _ T</h1><p class='error'>缺失错误信息描述文件</p><p class='detail'></p><p class='jump'>forfreej 在试图输出错误信息时, 无法在 webroot 中找到错误信息描述文件</p><p class='jump'>请在 webroot 目录下建立错误描述文件, 并以 error.html 命名</p><p class='jump'>这不是一个严重错误, 只会影响到您的调试, 不会影响到应用的正常运行与使用</p></div></body></html>");
		this.out.flush();
		this.out.close();
	}
	
	public void print_r(String str) {
		
	}
	
	public void print_r() {
		
	}
	
}