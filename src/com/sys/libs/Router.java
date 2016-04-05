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
		
		this.controller = Str.is_invalid(res._GET("c")) ? this.defaultController : res._GET("c");
		this.action = Str.is_invalid(res._GET("a")) ? this.defaultAction : res._GET("a");
		this.pack = Str.is_invalid(res._GET("p")) ? this.defaultPack : res._GET("p");
		
		
		if ( Str.is_invalid(this.controller) || Str.is_invalid(this.action) || Str.is_invalid(this.pack) ) {
			
			/*try {
				//new ControllerBase(this, out).error( new String[] {"../webapps/forfreej/error.html"} );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

		} else {
			try {
				Class<?> ctrl = Class.forName("app.controller." + this.pack + "." + Str.toUpper(this.controller, 0) + "Controller"); //获取控制器
				
				ctrl.getMethod("init", RequestInfo.class, ResponseInfo.class, String.class).invoke(ctrl.newInstance(), res, resq, this.action);
				
			} catch (NoSuchMethodException e) {
				try {
					this.cb.error( new String[] {"../webapps/forfreej/error.html"} );
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//反射的方法不存在
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				try {
					this.cb.error( new String[] {
						"asdasda",
						"<br>请查看app.controller." + pack + "." + Str.toUpper(this.controller, 0) + "Controller是否存在"
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
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

	public String getDefaultController() {
		return defaultController;
	}

	public String getDefaultAction() {
		return defaultAction;
	}

	public String getDefaultPack() {
		return defaultPack;
	}

	public String getController() {
		return controller;
	}

	public String getAction() {
		return action;
	}

	public String getPack() {
		return pack;
	}
} 