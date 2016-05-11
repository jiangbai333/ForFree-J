package com.sys.libs.db.base;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public interface Db {
	
	/**
	 * sql语句中表名
	 * @param table
	 * @return
	 */
	public Db table(String table);
	
	/**
	 * sql语句中列名
	 * mysql.field("field1, field2, ..., fieldn");
	 * @param String field
	 * @return
	 */
	public Db field(String field);
	
	/**
	 * sql语句中列名
	 * mysql.field(new ArrayList<String>(){{
	 * 		add("field1");
	 * 		add("field2");
	 * 		...
	 * 		add("fieldn");
	 * }});
	 * @param List<String> field
	 * @return
	 */
	public Db field(List<String> field);

	/**
	 * insert、update的数据
	 * @param data
	 * @return
	 */
	public Db data(Map data);
	
	/**
	 * sql语句中where查询条件
	 * @param where
	 * @return
	 */
	public Db where(String where);
	
	/**
	 * 构建插入字符串
	 * @throws SQLException
	 */
	public int add() throws SQLException;
	
	/**
	 * 构建删除字符串
	 * @return int 影响到的行数
	 * @throws SQLException
	 */
	public int del() throws SQLException;
	
	/**
	 * 构建更新字符串
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int update() throws SQLException;	
	
	/**
	 * 构建查询字符串
	 * @return 查询到的结果集
	 */
	public List< Map<String, String> > select();
	
	/**
	 * 根据指定sql语句执行更改操作
	 * @param sql
	 * @return 影响到的行数
	 * @throws SQLException 
	 */
	public int execute(String sql) throws SQLException;
		
	/**
	 * 根据指定sql语句进行查询
	 * @param sql
	 * @return
	 */
	public List< Map<String, String> > query(String sql);
}