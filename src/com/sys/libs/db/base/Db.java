package com.sys.libs.db.base;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Db {
	
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
	 * sql语句中表名
	 * @param table
	 * @return
	 */
	public Db table(String table);
	
	/**
	 * sql语句中where查询条件
	 * @param where
	 * @return
	 */
	public Db where(String where);

	/**
	 * insert、update的数据
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Db data(Map data);
	
	/**
	 * 构建插入字符串
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
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
	 */
	public int execute(String sql);
	
	/**
	 * 执行更改操作
	 * @return影响到的行数
	 */
	public int execute();
	
	/**
	 * 根据指定sql语句进行查询
	 * @param sql
	 * @return
	 */
	public List< Map<String, String> > query(String sql);
	
	/**
	 * 进行查询
	 * @param sql
	 * @return
	 */
	public List< Map<String, String> > query();
	
	
	
	
	
	
	
	
//	
//	
//	/**
//	 * 根据指定的sql语句 执行delete操作
//	 * @param sql
//	 * @return
//	 * @throws SQLException
//	 */
//	public int update(String sql) throws SQLException;
//	
//	/**
//	 * 执行update操作
//	 * @param data 
//	 * @return
//	 * @throws SQLException
//	 */
//	public int update(Map<String, String> data) throws SQLException;
//	
//
//	
//	/**
//	 * 根据给定的sql语句进行插入
//	 * @param sql
//	 * @throws SQLException
//	 */
//	public int add(String sql) throws SQLException;
//	
//	/**
//	 * 根据指定的数据进行插入
//	 * @param data
//	 * @throws SQLException
//	 */
//	public int add(Map<String, String> data) throws SQLException;
//	
//	

//	
//	/**
//	 * 获取行数
//	 * @return int 行数
//	 */
//	public int getRows();
//	
//	/**
//	 * 获取列数
//	 * @return 列数
//	 */
//	public int getCols();
//
//	/**
//	 * 事物提交
//	 * @throws SQLException
//	 */
//	public void commit() throws SQLException;
//	
//	/**
//	 * 事物回滚
//	 * @throws SQLException
//	 */
//	public void rollback() throws SQLException;
}