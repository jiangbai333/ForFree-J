package app.controller.auto;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import com.sys.core.controller.ControllerBase;
import com.sys.libs.Proper;
import com.sys.libs.db.Mysql;
import com.sys.tools.F;


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
		Mysql m = new Mysql(new Proper(F.P("mysql")).getProperties());
		JSONArray json = JSONArray.fromObject(m.table("test").field("*").select());
		this.str="test";
		this.out.write(json.toString());
	}		
}