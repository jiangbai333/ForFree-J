package com.sys.libs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sys.core.controller.ControllerBase;

@SuppressWarnings("serial")
public final class Template {
	
	public Map<String, Pattern> reg = new HashMap<String, Pattern>(){{
		
		put("lv1",Pattern.compile("\\{\\$([a-zA-Z]\\w*?)\\}"));
		put("lv2",Pattern.compile("\\{\\$([a-zA-Z]\\w*?\\.[a-zA-Z]\\w*?)\\}"));
		
	}};
	
	@SuppressWarnings("unchecked")
	public String compile(String file, ControllerBase cb) {
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));

			
			String	temp = "",
					str = "";
			

			while( null != ( temp = reader.readLine() ) ){
				 str += temp;
			}
			
			Class<?> cls= cb.getClass();
			
			Matcher m = reg.get("lv1").matcher(str);
			ArrayList<String> lv1Strs = new ArrayList<String>();
			while (m.find()) {
				lv1Strs.add(m.group(1));            
			} 
	        
			m = reg.get("lv2").matcher(str);
			ArrayList<String> lv2Strs = new ArrayList<String>();
			while (m.find()) {
				lv2Strs.add(m.group(1));            
			} 
	        
			for (String s : lv1Strs){
	        	
				str = str.replaceAll("\\{\\$" + s + "\\}", (String) cls.getField(s).get(cb));
			}
	        
			for (String s : lv2Strs){
	        	
				String paramKey = s.split("\\.")[0];
				String paramValue = s.split("\\.")[1];
	        	
				Map<String, String> t = (Map<String, String>) cls.getField(paramKey).get(cb);
				str = str.replaceAll("\\{\\$" + s + "\\}", t.get(paramValue));
				
			}
	        
			return str;
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "渲染失败";
	}
}