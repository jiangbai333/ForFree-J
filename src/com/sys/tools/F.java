package com.sys.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

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
	
	
	static Socket server;

	public static void main(String[] args) throws Exception {
		System.out.println(InetAddress.getLocalHost());
	   //server = new Socket(InetAddress.getLocalHost(), 34952);
		server = new Socket("192.168.9.120", 34952);
	   BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
	   PrintWriter out = new PrintWriter(server.getOutputStream());
	   BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));

	   while (true) {
	    String str = wt.readLine();
	    System.out.println();
	    out.println(str);
	    out.flush();
	    if (str.equals("end")) {
	     break;
	    }
	   }
	   server.close();
	}
}
