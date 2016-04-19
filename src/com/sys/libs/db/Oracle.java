package com.sys.libs.db;

import java.sql.SQLException;
import java.util.Map;

import com.sys.libs.db.base.DbBase;

public class Oracle extends DbBase{

	public Oracle(Map<String, String> conf) throws ClassNotFoundException, SQLException {
		super(conf, "oralce");
	}
}
