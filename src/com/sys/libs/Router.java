package com.sys.libs;

import java.lang.reflect.InvocationTargetException;

import com.sys.common.RequestInfo;
import com.sys.common.ResponseInfo;
import com.sys.core.controller.ControllerBase;
import com.sys.tools.Reflection;
import com.sys.tools.Str;

public final class Router {

	private RequestInfo res = null;

	private ResponseInfo resp = null;	
	
	private ControllerBase cb = null;
	
	public Router(RequestInfo res, ResponseInfo resp) {
		
		this.res = res;
		this.resp = resp;

		if ( Str.is_invalid(this.res._ROUTER("c")) ) {this.res._setCoreQueryString("c", "index");}
		if ( Str.is_invalid(this.res._ROUTER("a")) ) {this.res._setCoreQueryString("a", "index");}
		if ( Str.is_invalid(this.res._ROUTER("p")) ) {this.res._setCoreQueryString("p", "index");}				

		this.cb = new ControllerBase().init(res, resp, null);
		
		this.run();
	}
	
	public void run() {
		
		try {
/**将要进一步优化的部分，更高效的实例化控制器*/
//			new Reflection<ControllerBase>().todo(this.cb, this.res._ROUTER("a"));
			
			String cls = "app.controller." + res._ROUTER("p") + "." + Str.toUpper(res._ROUTER("c"), 0) + "Controller";
			
			new Reflection<ControllerBase>(cls).todo("init", new Class[] {
				RequestInfo.class, ResponseInfo.class, String.class
			}, new Object[] {
				this.res, this.resp, this.res._ROUTER("a")
			});
		} catch (NoSuchMethodException e) {
			//当核心控制器缺少 init 方法时，将抛出这个错误。但这是正常开发不可能遇到的，不用理会。
		} catch (ClassNotFoundException e) {
			//将要反射的类不存在时，抛出这个错误。
			this.cb.error("MissingController", new String[] {
				"请查看app.controller." + res._ROUTER("p") + "." + Str.toUpper(res._ROUTER("c"), 0) + "Controller 类是否存在"
			});
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}
		
	}
} 