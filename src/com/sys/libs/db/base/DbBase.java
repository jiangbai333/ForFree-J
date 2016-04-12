package com.sys.libs.db.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbBase implements Db {

	public int row = 0;
	
	public String sql = "";
	
	public Map< String, List< String > > queryMap = new HashMap< String, List< String > >();

	public Db field(List< String > field) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Db field(String field) {
		
		return this;
	}

	public Db table() {
		// TODO Auto-generated method stub
		return this;
	}

	public Db where() {
		
		return this;
	}
}
