package app.controller.auto;

import java.io.PrintWriter;

import com.sys.core.controller.ControllerBase;


public class IndexController extends ControllerBase {
	public String test = "测试模板引擎";
	public String test123 = "测试模板引擎123";

	public void index() {
//		T.N();
		this.display();
	}
}