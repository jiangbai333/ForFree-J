package com.sys.libs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Proper {
	
	@SuppressWarnings("unchecked")
	public Proper(Map<String, String> properties) {
		Map<String, String> tempMap = new HashMap<String, String>();
		
		Properties pro = new Properties();
		
		try {
			pro.load(new FileInputStream(""));
			
		    Enumeration enum1 = pro.propertyNames();//得到配置文件的名字
		    
		    while(enum1.hasMoreElements()) {
		    	
		        String key = (String) enum1.nextElement();
		        String value = pro.getProperty(key);
		        
		        tempMap.put(key, value);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
