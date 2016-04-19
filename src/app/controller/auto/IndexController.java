package app.controller.auto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sys.core.controller.ControllerBase;
import com.sys.libs.Proper;
import com.sys.libs.db.Mysql;


@SuppressWarnings("serial")
public class IndexController extends ControllerBase {
		
	public Map<String, String> title = new HashMap<String, String>(){{
		put("head", "一个测试页面");
		put("body", "forfreej0.3");
	}};
	
	public String str = "极致简约! 开发由我";

	public void index() {
//		T.N();
		 
		this.display();
	}
	
	public void test() {
//		T.N();
		//this.forward();
		Mysql m;
		try {
			m = new Mysql(new Proper(this.P("mysql")).getProperties());

			this.print_r(m.table("test").field("*").select());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}