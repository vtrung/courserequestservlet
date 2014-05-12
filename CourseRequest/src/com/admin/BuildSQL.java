package com.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;


public class BuildSQL {
	private String username = "root";
	private String password = "root";
	private String port = "8889";

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	private static String[] course = {
			"MATH 3",
			"MATH 5A",
			"MATH 5B",
			"MATH 5C",
			"MATH 7A",
			"MATH 7B",
			"MATH 9",
			"MATH 10",
			"MATH 15",
			"MATH 55",
			"MATH 125",
			"MATH 141",
			"MATH 131",
			"MATH 150",
			"MATH 250",
			"MATH 402",
			"MATH 450",
			"STAT 50",
			"CS 2",
			"CS 3A"
	};
	private static String[] times = {
			"Mon/Wed,7:00am,9:30am",
			"Mon/Wed,9:45am,12:15pm",
			"Mon/Wed,12:30pm,3:00pm",
			"Mon/Wed,3:15pm,5:45pm",
			"Mon/Wed,7:00pm,9:30pm",
			"Tue/Thur,7:00am,9:30am",
			"Tue/Thur,9:45am,12:15pm",
			"Tue/Thur,1:00pm,3:30pm",
			"Tue/Thur,3:45pm,6:15pm",
			"Tue/Thur,7:30pm,10:00pm",
			"MWF,7:00am,8:30am",
			"MWF,8:45am,10:15am",
			"MWF,10:30am,12:00pm",
			"MWF,12:15pm,1:45pm",
			"MWF,2:00pm,3:30pm",
			"MWF,3:45pm,5:15pm",
			"MWF,5:30pm,7:00pm",
			"MWF,7:15pm,8:45pm",
			"MWF,9:00pm,10:30pm",
			"MTRF/TWRF,7:30am,8:40am",
			"MTRF/TWRF,9:00am,10:10am",
			"MTRF/TWRF,10:30am,11:40am",
			"MTRF/TWRF,1:00pm,2:10pm",
			"MTRF/TWRF,2:30pm,3:40pm",
			"MTRF/TWRF,4:00pm,5:10pm",
			"MTRF/TWRF,5:30pm,6:40pm",
			"MTRF/TWRF,7:00pm,8:10pm",
			"MTRF/TWRF,8:30pm,9:40pm",
	};
	private static String[] sqlcommands = {
		"CREATE DATABASE schedule;",
		"USE schedule;",
		"CREATE TABLE instructor("
				+"faculty_id INT(8) NOT NULL,"
				+"last_name VARCHAR(255) NOT NULL,"
				+"full_time TINYINT(1) NOT NULL,"
				+"PRIMARY KEY (faculty_id)"
				+");",
		"CREATE TABLE courses("
				+"course_name VARCHAR(255) NOT NULL,"
				+" PRIMARY KEY (course_name)"
				+");",
		"CREATE TABLE timeblock("
				+"time_id INT(255) NOT NULL AUTO_INCREMENT,"
				+"day VARCHAR(255) NOT NULL,"
				+"begin VARCHAR(255) NOT NULL,"
				+"end VARCHAR(255) NOT NULL,"
				+"cs_or_not TINYINT(1) NOT NULL,"
				+"PRIMARY KEY (time_id)"
				+");",
		"CREATE TABLE request("
				+"request_id INT(255) NOT NULL AUTO_INCREMENT,"
				+"faculty_id INT(8) NOT NULL,"
				+"course_name VARCHAR(255) NOT NULL,"
				+"time_id INT(255) NOT NULL,"
				+"choice_order INT(255) NOT NULL,"
				+"note VARCHAR(255) NOT NULL,"
				+"PRIMARY KEY (request_id),"
				+"FOREIGN KEY (faculty_id) REFERENCES instructor(faculty_id),"
				+"FOREIGN KEY (course_name) REFERENCES courses(course_name),"
				+"FOREIGN KEY (time_id) REFERENCES timeblock(time_id)"
				+");",
	};

	
	public BuildSQL(){}
	
	//TESTING Method
	public static void main(String[] args) {
		//build();
	}

	public static void build(String port, String user, String pass){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"?user="+user+"&password="+pass);
			//con = DriverManager.getConnection("jdbc:mysql://localhost:8889?user=root&password=root");
			Statement stmt = (Statement) con.createStatement();
			/*ResultSet rs = stmt.executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'schedule'");
			if(!rs.next()){
				System.out.println("database does not exist");
			}else{
				System.out.println("database exist");
			};*/
			System.out.println(con.toString());
			for(int index = 0; index < sqlcommands.length; index++){
				stmt.executeUpdate(sqlcommands[index]);
			}
			addCourseData(stmt);
			addTimeData(stmt);
			con.close();
			
			
			System.out.println("Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addCourseData(Statement stmt) throws SQLException{
			for(int index = 0; index < course.length; index++){
					stmt.executeUpdate("INSERT INTO courses (course_name) VALUES "
						+"('"+ course[index] +"');");
			};
	}
	
	public static void addTimeData(Statement stmt) throws SQLException{
		Scanner in;
		String day, begin, end;
		
			for(int index = 0; index < times.length; index++){
				System.out.println("Begin Scan");
				in = new Scanner(times[index]);
				in.useDelimiter(",");
				System.out.println("Begin Scan2");
				day = in.next(); System.out.println(day);
				begin = in.next(); System.out.println(begin);
				end = in.next(); System.out.println(end);
				System.out.println("End Scan");
				stmt.executeUpdate(insertTime(day, begin, end, "1"));
			};
			
	}

	public static String insertTime(String day, String begin, String end, String cs){
		String result = "INSERT INTO timeblock (day, begin, end, cs_or_not) VALUES "
				+"('"+day+"','"+begin+"','"+end+"','"+cs+"');";
		return result;
	}
	
	public boolean checkSQL(){
		Connection con;
		try {
			con = DriverManager.getConnection(
			        "jdbc:mysql://localhost:8889?user=root&password=root");
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'schedule'");
			if(!rs.next()){
				return false;
			};
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Connection getConStatement() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889?user=root&password=root");
		return con;
	}
	
}
