package com.sys.tools;

import com.sys.libs.Template;

public class F {
	
	public String a = null;
	public Template t = null;
	
	public F(String a, Template t) {
		this.a = a;
		this.t = t;
		System.out.println(a);
		System.out.println(t.reg.get("lv1"));
		System.out.println(t.reg.get("lv2"));
	}
	
	public void test(int a){}
}
