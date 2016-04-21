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

public class DbBase implements Db {

	public int row = 0;
	
	public int col = 0;
	
	public String sql = "";
	
	public Map< String, List< String > > queryMap = new HashMap< String, List< String > >();
	
	protected String dbtype = null;
	
	protected String fieldStr = "";
	
	protected String tableStr = "";

	@SuppressWarnings("unchecked")
	protected Map<String, List<String>> data = new HashMap<String, List<String>>();
	
	protected String whereStr = "1";
	
	protected String host = null;

	protected String port = null;

	protected String dbname = null;

	protected String usernm = null;
	
	protected String passwd = null;
	
	protected ResultSet rs = null;
	
	private Connection conn = null;
	
	public DbBase(Map<String, String> conf, String dbtype) throws ClassNotFoundException, SQLException {
		
		this.dbtype = dbtype;
		this.host = conf.get("host");
		this.port = conf.get("port");
		this.dbname = conf.get("dbname");
		this.usernm = conf.get("usernm");
		this.passwd = conf.get("passwd");
		
		this.connect();
	}

	public void connect() throws ClassNotFoundException, SQLException {
		String	driver = null,
				url = null,
				username = null,
				password = null;
		
		if (this.dbtype.equals("mysql")) {
			driver = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname + "?characterEncoding=UTF-8";
		} else if (this.dbtype.equals("oracle")) {
			driver = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:oracle:thin:@" + this.host + ":" + this.port + ":" + this.dbname;
		}

		username = usernm;
		password = passwd;
		
		Class.forName(driver);
		this.conn = DriverManager.getConnection(url, username, password);
		this.conn.setAutoCommit(false);
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

	public DbBase data(Map<String, List<String>> data) {
		
		this.data = data;
		
		return this;
	}
	
	public List< Map<String, String> > select() {
		this.sql = "select " + this.fieldStr + " from " + this.tableStr + " where " + this.whereStr;
		return this.query();
	}
	
	public void add(String sql) throws SQLException {
		this.sql = sql;
		this.executeUpdate(sql);
	}
	
	public void add(Map<String, String> data) throws SQLException {
		StringBuffer sql = new StringBuffer("insert into `" + this.tableStr + "` ");
		String field = "";
		String value = "";
		
		Iterator<Entry<String, String>> iteratorMap = data.entrySet().iterator();  
		while (iteratorMap.hasNext()) {  
						
			Entry<String, String> entry = iteratorMap.next();  
        	field += "`" + entry.getKey() + "`,";
        	value += "'" + entry.getValue() + "',";  
		}
		
		sql.append("(" + field.substring(0, field.length() - 1) + ") VALUES ");
		sql.append("(" + value.substring(0, field.length() - 1) + ")");
		
		this.sql = sql.toString();
		this.executeUpdate(this.sql);
	}
	
	@SuppressWarnings("unchecked")
	public void add() throws SQLException {

		Map<String, List<String>> temp = this.data;
		
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

		this.executeUpdate(this.sql);
	}
	
	public List< Map<String, String> > query(String sql) {
		return this.executeQuery(sql);
	}
	
	private List< Map<String, String> > query() {
		return this.executeQuery(this.sql);
	}
	
	private List< Map<String, String> > executeQuery(String sql) {
		List< Map<String, String> > tempList = new ArrayList< Map<String, String> >();
		
		PreparedStatement pstmt;
		try {
	    	pstmt = this.conn.prepareStatement(sql);
	        
	        this.rs = pstmt.executeQuery();
	        this.row = this.GetRows();
	        this.col = this.GetCols();
	        
	        while (rs.next()) {
        		Map<String, String> tempMap = new HashMap<String, String>();
	        	for (int i = 1; i <= col; i++) {
	        		tempMap.put(rs.getMetaData().getColumnName(i), this.rs.getString(i));
	            }
	        	tempList.add(tempMap);
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return tempList;
	}
	
	private void executeUpdate(String sql) throws SQLException {
		
	    PreparedStatement pstmt = null;
	    try {
	    	
	    	pstmt = this.conn.prepareStatement(sql);
	        this.row = pstmt.executeUpdate();
	        this.conn.commit();
	    } catch (SQLException e) {
	    	
			this.conn.rollback();
	        e.printStackTrace();
	    } finally{
	    	
	    	if(pstmt != null) {
				pstmt.close();
	    	}
	    }
//        pstmt.close();
//        conn.close();
	}

	private int GetRows() {
		
		int result = 0;
	    try {
			if (this.rs.last()) {
				result = this.rs.getRow();
				this.rs.beforeFirst();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private int GetCols() {
		int col = 0;
		try {
			col = rs.getMetaData().getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return col;
	}

	
	protected void finalize() throws java.lang.Throwable {
		
		if(this.conn != null) {
			this.conn.close();
		}
        super.finalize();
     }
}
