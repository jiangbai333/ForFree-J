package com.sys.tools;

import com.App;

public class F {
	
	
	public static String P(String file) {
		
		return "../webapps/" + App.info.get("projectName") + "/WEB-INF/classes/" + file + ".properties";
	}
}
