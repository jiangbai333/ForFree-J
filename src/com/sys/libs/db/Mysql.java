package com.sys.libs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.sys.libs.db.base.DbBase;

public class Mysql extends DbBase {
		
	private Connection conn = null;
	
	public Mysql(Map<String, String> conf) {
		super(conf, "mysql");
	}
}