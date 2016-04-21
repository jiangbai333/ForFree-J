package app.controller.auto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	@SuppressWarnings("unchecked")
	public void test() {
//		T.N();
		//this.forward();
		Mysql m;
		try {
			m = new Mysql(new Proper(this.P("mysql")).getProperties());
			
			Map temp = new HashMap<String, List<String>>();
			List templis = new ArrayList<String>();
			
			for(int i = 1; i<=100;i++){
				templis.add("text测试" + i);
				
			}
			temp.put("text", templis);
			
			m.table("test").data(temp).add();
			
			//this.print_r(m.table("test").field("*").select());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}