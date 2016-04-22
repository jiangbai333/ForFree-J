package app.model.auto.index;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sys.core.controller.ControllerBase;
import com.sys.core.model.ModelBase;
import com.sys.libs.Proper;
import com.sys.libs.db.Mysql;

public class Test1Model extends ModelBase {

	public int a = 1;
	
	public Test1Model(ControllerBase cb) {
		super(cb);
	}
	
	public List<Map<String, String>> test() {
		Mysql m = null;
		try {
			m = new Mysql(new Proper(cb.P("mysql")).getProperties());
			
			return m.table("test").field("*").where("id>100000 AND id<100900").select();
			
			//this.print_r(m.table("test").field("*").select());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}