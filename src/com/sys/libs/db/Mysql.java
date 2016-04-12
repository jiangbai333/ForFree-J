package com.sys.libs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.sys.libs.db.base.DbBase;

public class Mysql extends DbBase {
	
	private String host = null;

	private String port = "3306";

	private String dbname = null;

	private String usernm = null;
	
	private String passwd = null;
	
	private Connection conn = null;
	
	public Mysql(Map<String, String> conf) {
		
	}

	public Boolean connect() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://" + host + ":" + port + "3306/" + dbname;
		String username = usernm;
		String password = passwd;
		try {
			Class.forName(driver);
			this.conn = (Connection) DriverManager.getConnection(url, username, password);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}