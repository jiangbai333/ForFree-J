package com.sys.libs.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.sys.libs.db.base.DbBase;

public class Oracle extends DbBase{

	public Oracle(Map<String, String> conf) throws ClassNotFoundException, SQLException {
		super(conf, "oralce");
		this.connect();
	}
	
	protected void connect() throws ClassNotFoundException, SQLException {
		String	username = usernm,
				password = passwd,
				driver = "oracle.jdbc.driver.OracleDriver",
				url = "jdbc:oracle:thin:@" + this.host + ":" + this.port + ":" + this.dbname;
		
		Class.forName(driver);
		this.conn = DriverManager.getConnection(url, username, password);
		this.conn.setAutoCommit(false);
	}
}
