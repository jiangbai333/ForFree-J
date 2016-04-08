package com.sys.libs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public final class Template {
	
	public Map<String, Pattern> reg = new HashMap<String, Pattern>(){{
		put("lv1",Pattern.compile("\\{\\$(.+?)\\}"));
		put("lv2",Pattern.compile("\\{\\$(.+?)\\}"));
	}};//Pattern.compile("\\{\\$(.+?)\\}");
	public Pattern p = Pattern.compile("\\{\\$(.+?)\\}");
	
}