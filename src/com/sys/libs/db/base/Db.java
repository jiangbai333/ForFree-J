package com.sys.libs.db.base;

import java.util.List;

public interface Db {
	
	public Db field(String field);
	
	public Db field(List<String> field);
		
	public Db table(String table);
	
	public Db where(String where);
}