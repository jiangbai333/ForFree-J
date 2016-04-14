package com.sys.libs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Proper {
	
	private Map<String, String> properties = new HashMap<String, String>();
	
	@SuppressWarnings("unchecked")
	public Proper(String prop) {
		
		Properties pro = new Properties();
		
		try {
			pro.load(new FileInputStream(prop));
			
		    Enumeration enum1 = pro.propertyNames();//得到配置文件的名字
		    
		    while(enum1.hasMoreElements()) {
		    	
		        String key = (String) enum1.nextElement();
		        String value = pro.getProperty(key);
		        
		        this.properties.put(key, value);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, String> getProperties() {
		return properties;
	}
}
