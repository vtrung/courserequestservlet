package com.admin;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class AdminBean {
	private String port;
	private String user;
	private String pass;
	private XMLwriter writer = new XMLwriter();
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	private String write(){
		try {
			writer.connectXML(this.port, this.user, this.pass);
			return "Success";
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
	}
	
	public String build(){
		write();
		read();
 		BuildSQL.build(this.port,this.user,this.pass);
 		return "success";
 	}
	
	private String read(){
		try {
			writer.connectParse();
			this.port = writer.getPort();
			this.user = writer.getUser();
			this.pass = writer.getPass();
			return "Success";
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
		
	}
	
}
