package com.sys.libs.db.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbBase implements Db {

	public int row = 0;
	
	protected String fieldStr = "";
	
	protected String tableStr = "";
	
	protected String whereStr = "";
	
	public String sql = "";
	
	public Map< String, List< String > > queryMap = new HashMap< String, List< String > >();
	
	public DbBase(Map<String, String> conf) {
		
	}

	public DbBase field( List< String > field) {
		
		String str = "";
		
		for(String temp : field){
			str += ( temp + "," );
		}
		
		this.fieldStr = str.substring(0, str.length() - 2);
		
		return this;
	}
	
	public DbBase field(String field) {
		
		this.fieldStr = field;
		
		return this;
	}

	public DbBase table(String table) {

		this.tableStr = table;
		
		return this;
	}

	public DbBase where(String where) {
		
		this.whereStr = where;
		
		return this;
	}
	
	public void select() {}
}
