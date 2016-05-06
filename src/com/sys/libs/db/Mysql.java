package com.sys.libs.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.sys.libs.db.base.DbBase;

public class Mysql extends DbBase {
			
	public Mysql(Map<String, String> conf) throws ClassNotFoundException, SQLException {
		super(conf, "mysql");
		this.connect();
	}
	
	protected void connect() throws ClassNotFoundException, SQLException {
		String	username = usernm,
				password = passwd,
				driver = "com.mysql.jdbc.Driver",
				url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname + "?characterEncoding=UTF-8";
		
		Class.forName(driver);
		this.conn = DriverManager.getConnection(url, username, password);
		this.conn.setAutoCommit(false);
	}
}