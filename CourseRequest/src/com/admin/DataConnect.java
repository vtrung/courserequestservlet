package com.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.mysql.jdbc.Statement;

public class DataConnect {
	
	public static Connection connect(){
		XMLwriter xml = new XMLwriter();
		try {
			xml.connectParse();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:"+xml.getPort()+"?user="+xml.getUser()+"&password="+xml.getPass());
			return con;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DataConnect connect() failed");
		return null;
	}
	
	public static void runUpdate(String query){
		Connection con = connect();
		try {
			Statement stmt = (Statement) con.createStatement();
			stmt.executeUpdate("USE schedule;");
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ResultSet runQuery(String query){
		Connection con = connect();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate("USE schedule;");
			ResultSet rs = stmt.executeQuery(query);
			return rs;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void insertInstructor(String id, String name, String fulltime){
		String query = "INSERT INTO instructor VALUES ('"
				+ id
				+ "','"
				+ name
				+ "','"
				+ fulltime
				+ "');";
		
				//DataConnect.runUpdate("INSERT INTO schedule.request VALUES (NULL,'101','Math 10','2','1');")
		runUpdate(query);
	}
	
	public static ResultSet getTimeList(){
		ResultSet rs = runQuery("SELECT * FROM timeblock");
		return rs;
	}
	
	public static int getTimeID(String time){
		Scanner in = new Scanner(time);
		in.useDelimiter(",");
		String day = in.next(); System.out.println(day);
		String begin = in.next(); System.out.println(begin);
		String end = in.next(); System.out.println(end);
		in.close();
		System.out.println("getTimeID started");
		ResultSet rs = runQuery("SELECT time_id FROM schedule.timeblock WHERE "
				+ "day='" + day
				+ "' AND begin='" + begin
				+ "' AND end='" + end
				+ "';");
		try {
			if(rs.next()){
				return rs.getInt("time_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static ResultSet getTime(String timeid){
		ResultSet rs = runQuery("select *"
								+ " from timeblock "
								+ "inner join request"
								+ " ON request.time_id="
								+ timeid);
		return rs;
	}
	
	public static ResultSet getInstructor(String facultyid){
		ResultSet rs = runQuery("select *"
								+ " from instructor "
								+ "inner join request"
								+ " ON request.facultyid="
								+ facultyid);
		return rs;
	}
	
	public static ResultSet getRequestList(){
		System.out.println("RequestList start");
		ResultSet rs = runQuery("SELECT * FROM request"
				+" INNER JOIN instructor"
				+" ON request.faculty_id=instructor.faculty_id"
				+" INNER JOIN timeblock"
				+" ON request.time_id=timeblock.time_id;");
		
		System.out.println("RequestList success");
		return rs;
	}
	
}