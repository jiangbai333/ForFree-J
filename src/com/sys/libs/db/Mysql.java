package com.sys.libs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.sys.libs.db.base.DbBase;

public class Mysql extends DbBase {
	
	private String host = "192.168.9.120";

	private String port = "3306";

	private String dbname = "baichao";

	private String usernm = "baichao";
	
	private String passwd = "13114626726";
	
	private Connection conn = null;
	
	public Mysql(Map<String, String> conf) {
		super(conf);
		this.connect();
	}

	public Boolean connect() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
		String username = usernm;
		String password = passwd;
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, username, password);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void select() {
		this.sql = "select " + this.fieldStr + " from " + this.tableStr + " where " + this.whereStr;

		PreparedStatement pstmt;
	    try {
	        pstmt = this.conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	            for (int i = 1; i <= col; i++) {
	                System.out.print(rs.getString(i) + "\t");
	                if ((i == 2) && (rs.getString(i).length() < 8)) {
	                    System.out.print("\t");
	                }
	             }
	            System.out.println("");
	        }
	            System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}