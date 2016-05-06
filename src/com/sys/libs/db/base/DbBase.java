package com.sys.libs.db.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class DbBase implements Db {

	public int row = 0;
	
	public int col = 0;
	
	public String sql = "";
	
	public Map< String, List< String > > queryMap = new HashMap< String, List< String > >();
	
	protected String dbtype = null;
	
	protected String fieldStr = "";
	
	protected String tableStr = "";

	@SuppressWarnings("unchecked")
	protected Map data = new HashMap();
	
	protected String whereStr = "1";
	
	protected String host = null;

	protected String port = null;

	protected String dbname = null;

	protected String usernm = null;
	
	protected String passwd = null;
	
	protected ResultSet rs = null;
	
	protected Connection conn = null;
	
	public DbBase(Map<String, String> conf, String dbtype) throws ClassNotFoundException, SQLException {
		
		this.dbtype = dbtype;
		this.host = conf.get("host");
		this.port = conf.get("port");
		this.dbname = conf.get("dbname");
		this.usernm = conf.get("usernm");
		this.passwd = conf.get("passwd");
	}

	protected abstract void connect() throws ClassNotFoundException, SQLException;

	public Db table(String table) {

		this.tableStr = table;
		
		return this;
	}

	public Db field(String field) {

		this.fieldStr = field;
		
		return this;
	}

	public Db field(List<String> field) {
		
		String str = "";
		
		for(String temp : field){
			str += ( temp + "," );
		}
		
		this.fieldStr = str.substring(0, str.length() - 2);
		
		return this;
	}

	@SuppressWarnings("unchecked")
	public Db data(Map data) {

		this.data = data;
		
		return this;
	}

	public Db where(String where) {

		this.whereStr = where;
		
		return this;
	}

	public int add() throws SQLException {
		Map temp = this.data;
		
		StringBuffer sql = new StringBuffer("insert into `" + this.tableStr + "` ");
		String field = "";
		List<String> value = new ArrayList();
		
		Iterator<Entry<String, List<String>>> iteratorMap = temp.entrySet().iterator();  
		while (iteratorMap.hasNext()) {  
			int i = 0;
			
			Entry<String, List<String>> entry = iteratorMap.next();  
        	field += "`" + entry.getKey() + "`,";
            
            Iterator<String> iteratorList = entry.getValue().iterator();  
			if ( value.isEmpty() ) {
				while (iteratorList.hasNext()) {  
					value.add("'" + iteratorList.next() + "',");
				}  
			} else {
				while (iteratorList.hasNext()) {
					String str = value.get(i);
					value.set(i++, str + "'" + iteratorList.next() + "',");
				}
			} 
		}
		
		sql.append("(" + field.substring(0, field.length() - 1) + ") VALUES ");
		
		int size = value.size();
		
		for (int x = 0; x < size; x++) {
			String str = value.get(x);
			String tempStr = str.substring(0, str.length() - 1);
			sql.append("(" + tempStr + "),");
		}
				
		this.sql = sql.toString().substring(0, sql.length() - 1);

		//return this.executeUpdate(this.sql);
		return 0;
	}

	public int del() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Map<String, String>> select() {
		// TODO Auto-generated method stub
		return null;
	}

	public int execute(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int execute() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Map<String, String>> query(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, String>> query() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
//	public DbBase field( List< String > field) {
//		
//		String str = "";
//		
//		for(String temp : field){
//			str += ( temp + "," );
//		}
//		
//		this.fieldStr = str.substring(0, str.length() - 2);
//		
//		return this;
//	}
//	
//	public DbBase field(String field) {
//		
//		this.fieldStr = field;
//		
//		return this;
//	}
//
//	public DbBase table(String table) {
//
//		this.tableStr = table;
//		
//		return this;
//	}
//
//	public DbBase where(String where) {
//		
//		this.whereStr = where;
//		
//		return this;
//	}
//
//	@SuppressWarnings("unchecked")
//	public DbBase data(Map data) {
//		
//		this.data = data;
//		
//		return this;
//	}
//	
//	/**
//	 * 构建查询字符串
//	 * @return
//	 */
//	public List< Map<String, String> > select() {
//		this.sql = "select " + this.fieldStr + " from `" + this.tableStr + "` where " + this.whereStr;
//		return this.query(this.sql);
//	}
//	
//	public int del() throws SQLException {
//		this.sql = "delete from `" + this.tableStr + "` where " + this.whereStr;
//		return this.executeUpdate(this.sql);
//	}
//	
//	public int update(Map<String, String> data) throws SQLException {
//		
//		StringBuffer sql = new StringBuffer("update `" + this.tableStr + "` set ");
//		String tempSql = "";
//		
//		Iterator<Entry<String, String>> iteratorMap = data.entrySet().iterator();  
//		while (iteratorMap.hasNext()) {  
//						
//			Entry<String, String> entry = iteratorMap.next();  
//			sql.append("`" + entry.getKey() + "` = '" + entry.getValue() + "',");
//		}
//		
//		tempSql = sql.toString().substring(0, sql.toString().length() - 1);
//		
//		this.sql = tempSql + " where " + this.whereStr;
//		return this.executeUpdate(this.sql);
//	}
//	
//	/**
//	 * 根据给定的sql语句进行插入
//	 * @param sql
//	 * @throws SQLException
//	 */
//	public int add(String sql) throws SQLException {
//		this.sql = sql;
//		return this.executeUpdate(sql);
//	}
//	
//	/**
//	 * 根据指定的数据进行插入
//	 * @param data
//	 * @throws SQLException
//	 */
//	public int add(Map<String, String> data) throws SQLException {
//		StringBuffer sql = new StringBuffer("insert into `" + this.tableStr + "` ");
//		String field = "";
//		String value = "";
//		
//		Iterator<Entry<String, String>> iteratorMap = data.entrySet().iterator();  
//		while (iteratorMap.hasNext()) {  
//						
//			Entry<String, String> entry = iteratorMap.next();  
//        	field += "`" + entry.getKey() + "`,";
//        	value += "'" + entry.getValue() + "',";  
//		}
//		
//		sql.append("(" + field.substring(0, field.length() - 1) + ") VALUES ");
//		sql.append("(" + value.substring(0, value.length() - 1) + ")");
//		
//		this.sql = sql.toString();
//		return this.executeUpdate(this.sql);
//	}
//	
//	/**
//	 * 插入数据
//	 * @throws SQLException
//	 */
//	@SuppressWarnings("unchecked")
//	public int add() throws SQLException {
//
//		Map<String, List<String>> temp = this.data;
//		
//		StringBuffer sql = new StringBuffer("insert into `" + this.tableStr + "` ");
//		String field = "";
//		List<String> value = new ArrayList();
//		
//		Iterator<Entry<String, List<String>>> iteratorMap = temp.entrySet().iterator();  
//		while (iteratorMap.hasNext()) {  
//			int i = 0;
//			
//			Entry<String, List<String>> entry = iteratorMap.next();  
//        	field += "`" + entry.getKey() + "`,";
//            
//            Iterator<String> iteratorList = entry.getValue().iterator();  
//			if ( value.isEmpty() ) {
//				while (iteratorList.hasNext()) {  
//					value.add("'" + iteratorList.next() + "',");
//				}  
//			} else {
//				while (iteratorList.hasNext()) {
//					String str = value.get(i);
//					value.set(i++, str + "'" + iteratorList.next() + "',");
//				}
//			} 
//		}
//		
//		sql.append("(" + field.substring(0, field.length() - 1) + ") VALUES ");
//		
//		int size = value.size();
//		
//		for (int x = 0; x < size; x++) {
//			String str = value.get(x);
//			String tempStr = str.substring(0, str.length() - 1);
//			sql.append("(" + tempStr + "),");
//		}
//				
//		this.sql = sql.toString().substring(0, sql.length() - 1);
//
//		return this.executeUpdate(this.sql);
//	}
//	
//	/**
//	 * 根据指定sql语句进行查询
//	 * @param sql
//	 * @return
//	 */
//	public List< Map<String, String> > query(String sql) {
//		return this.executeQuery(sql);
//	}
//	
//	/**
//	 * 执行查询
//	 * @param sql
//	 * @return List< Map<String, String> > 结果集列表
//	 */
//	private List< Map<String, String> > executeQuery(String sql) {
//		List< Map<String, String> > tempList = new ArrayList< Map<String, String> >();
//		
//		PreparedStatement pstmt;
//		try {
//	    	pstmt = this.conn.prepareStatement(sql);
//	        
//	        this.rs = pstmt.executeQuery();
//	        this.row = this.getRows();
//	        this.col = this.getCols();
//	        
//	        while (rs.next()) {
//        		Map<String, String> tempMap = new HashMap<String, String>();
//	        	for (int i = 1; i <= col; i++) {
//	        		tempMap.put(rs.getMetaData().getColumnName(i), this.rs.getString(i));
//	            }
//	        	tempList.add(tempMap);
//	        }
//	    } catch (SQLException e) {
//	    	e.printStackTrace();
//	    }
//		return tempList;
//	}
//	
//	/**
//	 * 执行更改
//	 * @param sql
//	 * @throws SQLException
//	 */
//	private int executeUpdate(String sql) throws SQLException {
//		
//	    PreparedStatement pstmt = null;
//	    	
//		pstmt = this.conn.prepareStatement(sql);
//		this.row = pstmt.executeUpdate();
//	    
//    	if(pstmt != null) {
//    		pstmt.close();
//    	}
//    	
//    	return this.row;
//	}
//
//	/**
//	 * 获取行数
//	 * @return int 行数
//	 */
//	public int setRows() {
//		
//		int result = 0;
//	    try {
//			if (this.rs.last()) {
//				result = this.rs.getRow();
//				this.rs.beforeFirst();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
//	
//	public int getRows() {
//		
//		return this.row;
//	}
//	
//	/**
//	 * 获取列数
//	 * @return 列数
//	 */
//	public int getCols() {
//		int col = 0;
//		try {
//			col = rs.getMetaData().getColumnCount();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return col;
//	}
//
//	/**
//	 * 事物提交
//	 * @throws SQLException
//	 */
//	public void commit() throws SQLException {
//		this.conn.commit();
//	}
//	
//	/**
//	 * 事物回滚
//	 * @throws SQLException
//	 */
//	public void rollback() throws SQLException {
//		this.conn.rollback();
//	}
//	
//	/**
//	 * 主动释放数据库连接
//	 * @throws SQLException
//	 */
//	public void release() throws SQLException{
//		this.conn.close();
//	}
//	
//	protected void finalize() throws java.lang.Throwable {
//		
//		if(this.conn != null) {
//			this.conn.close();
//		}
//        super.finalize();
//     }
}
