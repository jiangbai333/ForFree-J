package com.sys.libs.db.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbBase implements Db {

	public int row = 0;
	
	public int col = 0;
	
	public String sql = "";
	
	public Map< String, List< String > > queryMap = new HashMap< String, List< String > >();
	
	protected String dbtype = null;
	
	protected String fieldStr = "";
	
	protected String tableStr = "";
	
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
			url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname;
		} else if (this.dbtype.equals("oracle")) {
			driver = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:oracle:thin:@" + this.host + ":" + this.port + ":" + this.dbname;
		}

		username = usernm;
		password = passwd;
		
		Class.forName(driver);
		this.conn = DriverManager.getConnection(url, username, password);
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
	
	public List< Map<String, String> > select() {
		this.sql = "select " + 
					this.fieldStr + 
					" from " + 
					this.tableStr + 
					" where " + 
					this.whereStr;
		return this.query();
	}
	
	public List< Map<String, String> > query(String sql) {
		return this.execute(sql);
	}
	
	private List< Map<String, String> > query() {
		return this.execute(this.sql);
	}
	
	private List< Map<String, String> > execute(String sql) {
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

}
