package app.controller.auto;

import java.io.PrintWriter;

import com.sys.core.controller.ControllerBase;


public class IndexController extends ControllerBase {
	
	public String title = "一个测试页面";
	
	public String str = "极致简约! 开发由我";

	public void index() {
//		T.N();
		this.display();
	}
	
	public void test() {
//		T.N();
		this.out.write("ajax 返回");
	}
}