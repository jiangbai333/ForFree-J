package com.sys.libs.db.base;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Db {
	
	public Db field(String field);
	
	public Db field(List<String> field);
		
	public Db table(String table);
	
	public Db where(String where);

	@SuppressWarnings("unchecked")
	public Db data(Map data);
	
	/**
	 * 构建查询字符串
	 * @return
	 */
	public List< Map<String, String> > select();
	
	public int del() throws SQLException;
	
	public int update(Map<String, String> data) throws SQLException;
	
	/**
	 * 根据给定的sql语句进行插入
	 * @param sql
	 * @throws SQLException
	 */
	public int add(String sql) throws SQLException;
	
	/**
	 * 根据指定的数据进行插入
	 * @param data
	 * @throws SQLException
	 */
	public int add(Map<String, String> data) throws SQLException;
	
	/**
	 * 插入数据
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public int add() throws SQLException;
	
	/**
	 * 根据指定sql语句进行查询
	 * @param sql
	 * @return
	 */
	public List< Map<String, String> > query(String sql);
	
	/**
	 * 获取行数
	 * @return int 行数
	 */
	public int getRows();
	
	/**
	 * 获取列数
	 * @return 列数
	 */
	public int getCols();

	/**
	 * 事物提交
	 * @throws SQLException
	 */
	public void commit() throws SQLException;
	
	/**
	 * 事物回滚
	 * @throws SQLException
	 */
	public void rollback() throws SQLException;
}