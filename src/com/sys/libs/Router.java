package com.sys.libs;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

//import com.sys.core.controller.ControllerBase;
import com.sys.common.RequestInfo;
import com.sys.common.ResponseInfo;
import com.sys.core.controller.ControllerBase;
import com.sys.tools.Str;

public final class Router {
		
	private String defaultController = "index";
	
	private String defaultAction = "index";
	
	private String defaultPack = "index";
	
	private String controller = null;
	
	private String action = null;
	
	private String pack = null;
	
	public String objectName = null;
	
	private ControllerBase cb = null;
	
	public Router() {}
	
	public void run(RequestInfo res, ResponseInfo resq) {
		
		this.cb = new ControllerBase();
		this.cb.init(res, resq, null);
		
		this.controller = Str.is_invalid(res._GET("c")) ? 
				res._setCoreQueryString("c", this.defaultController) : res._GET("c");
		
		this.action = Str.is_invalid(res._GET("a")) ? 
				res._setCoreQueryString("a", this.defaultAction) : res._GET("a");
		
		this.pack = Str.is_invalid(res._GET("p")) ? 
				res._setCoreQueryString("p", this.defaultPack) : res._GET("p");
		
		try {
			Class<?> ctrl = Class.forName("app.controller." + this.pack + "." + Str.toUpper(this.controller, 0) + "Controller"); //获取控制器
			
			ctrl.getMethod("init", RequestInfo.class, ResponseInfo.class, String.class).invoke(ctrl.newInstance(), res, resq, this.action);
			
		} catch (NoSuchMethodException e) {
			try {
				this.cb.error( new String[] {""} );
			} catch (IOException e1) {
				//文件不存在
				e1.printStackTrace();
			}
			//反射的方法不存在
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			try {
				this.cb.error( new String[] {
					"请查看app.controller." + pack + "." + Str.toUpper(this.controller, 0) + "Controller是否存在"
				});
			} catch (IOException e1) {
				//文件不存在
				e1.printStackTrace();
			}
			//反射的类不存在
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}
		
	}
} 