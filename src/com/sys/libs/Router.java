package com.sys.libs;

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
	
	private ControllerBase cb = null;
	
	public Router() {}
	
	public void run(RequestInfo res, ResponseInfo resp) {
		
		this.init(res, resp);		
		
		try {
			Class<?> ctrl = Class.forName("app.controller." + this.pack + "." + Str.toUpper(this.controller, 0) + "Controller"); //获取控制器
			
			ctrl.getMethod("init", RequestInfo.class, ResponseInfo.class, String.class).invoke(ctrl.newInstance(), res, resp, this.action);
			
		} catch (NoSuchMethodException e) {
			//当核心控制器缺少 init 方法时，将抛出这个错误。但这是正常开发不可能遇到的，不用理会。
		} catch (ClassNotFoundException e) {
			//将要反射的类不存在时，抛出这个错误。
			this.cb.error("MissingController", new String[] {
				"请查看app.controller." + pack + "." + Str.toUpper(this.controller, 0) + "Controller 类是否存在"
			});
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}
		
	}
	
	private void init(RequestInfo res, ResponseInfo resp) {

		this.cb = new ControllerBase();
		this.cb.init(res, resp, null);
		
		this.controller = Str.is_invalid(res._GET("c")) ? 
				res._setCoreQueryString("c", this.defaultController) : res._GET("c");
		
		this.action = Str.is_invalid(res._GET("a")) ? 
				res._setCoreQueryString("a", this.defaultAction) : res._GET("a");
		
		this.pack = Str.is_invalid(res._GET("p")) ? 
				res._setCoreQueryString("p", this.defaultPack) : res._GET("p");
	}
} 