package app.controller.auto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import redis.clients.jedis.Jedis;

import app.model.auto.index.Test1Model;

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
	public void amaze() {
//		T.N();
		 
		this.display();
	}
	public void redis() {
		      //连接本地的 Redis 服务
	      Jedis jedis = new Jedis("192.168.9.120");
	      System.out.println("Connection to server sucessfully");
	      //查看服务是否运行
	     System.out.println(jedis.get("a"));
	 
	}
	
	public void up() {
		
		Mysql m;
		try {
			m = new Mysql(new Proper(this.P("mysql")).getProperties());
			
			Map<String, String> temp = new HashMap<String, String>(){{
				put("text", "asdf");
				put("test", "a123123");
				put("test1", "b3213123啊啊");
				put("test2", "a1231231啊啊");
			}};
			
			m.table("test").data(temp).where("id>28").update();
			
			m.commit();
			
			System.out.println(m.getRows());
			
			//this.print_r(m.table("test").field("*").select());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			
			for(int i = 1; i<=100000;i++){
				templis.add("text测试" + i);
				
			}
			temp.put("text", templis);
			
			m.table("test").data(temp).add();
			
			//m.table("test").where("id>28").del();
			
			m.commit();
			
			System.out.println(m.getRows());
			
			//this.print_r(m.table("test").field("*").select());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void test1() {
		Test1Model a = (Test1Model) this.M();
		this.print_r(a.test());
	}
	
	public void test2() {
		Socket server = null;
		   
		try {
			
			server = new Socket("192.168.9.120", 34952);
				
			BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
				
			PrintWriter out = new PrintWriter(server.getOutputStream());

		   System.out.println(in.readLine());
		    
		    this.echo(str, true);
//		    if (str.equals("end")) {
//		     break;
//		    }
		   
		   server.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}