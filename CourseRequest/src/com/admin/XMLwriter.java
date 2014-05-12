package com.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

public class XMLwriter {
	private String port = null;
	private String user = null;
	private String pass = null;
	
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
	
	public XMLwriter(){}
	
	public void connectXML(String port, String user, String pass) throws ParserConfigurationException{
		String filename = "connect.xml";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		
		Element body = doc.createElement("Connect");
		doc.appendChild(body);
		
		Element e = doc.createElement("Port");
		Text t = doc.createTextNode(port);
		e.appendChild(t);
		body.appendChild(e);
		
		Element e1 = doc.createElement("User");
		Text t1 = doc.createTextNode(user);
		e1.appendChild(t1);
		body.appendChild(e1);
		
		Element e2 = doc.createElement("Pass");
		Text t2 = doc.createTextNode(pass);
		e2.appendChild(t2);
		body.appendChild(e2);
		
		DOMImplementation impl = doc.getImplementation();
		DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
		LSSerializer ser = implLS.createLSSerializer();
		LSOutput lsOutput =  implLS.createLSOutput();
		lsOutput.setEncoding("UTF-8");
		//System.out.println(lsOutput.toString());
		Writer stringWriter = new StringWriter();
		lsOutput.setCharacterStream(stringWriter);
		ser.write(doc, lsOutput);     
		String result = stringWriter.toString();
		System.out.println(result);
		//System.out.println(ser.write(doc,lsOutput));
		try {
			print(filename,result);
			System.out.println("Print Success");
			File file = new File(filename);
			System.out.println(file.getCanonicalPath());
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
			System.out.println("print failed");
		}
		
	}
	
	public void print(String filename, String text) throws IOException{
		PrintWriter out = new PrintWriter(filename);
		out.write(text);
		out.close();
		File file = new File(filename);
		System.out.println(file.getPath());
	}
	
	public void connectParse() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException{
		String fileName = "connect.xml";
		File f = new File(fileName); 
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(f);
		
		XPathFactory xpfactory = XPathFactory.newInstance();
		XPath path = xpfactory.newXPath();
		
		
		//System.out.println(f.getAbsolutePath());
		this.port = path.evaluate("/Connect/Port", doc);
		System.out.println(port);
		this.user = path.evaluate("/Connect/User", doc);
		System.out.println(user);
		this.pass = path.evaluate("/Connect/Pass", doc);
		System.out.println(pass);
	}
}
