package scheduler;


import Classes.Course;
import Classes.Instructor;
import Classes.TimeBlock;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class DbConnection {

	private XMLwriter dbData;
	private ArrayList<Course> courses;
//	public DbConnection(){
//		//iniData();
//	}

	public DbConnection(XMLwriter db){
		dbData = db;
	}
	
	
	
	public ArrayList<Course> getCourseData(){
		//Accessing driver from the JAR file
		try {
			//Accessing driver from the JAR file
			Class.forName("com.mysql.jdbc.Driver");
			
			//connecting to the database
			//jdbc:mysql://localhost:3306/schedule --> schedule is the database name
			//root is the database user 1st
			//root is the database password 2nd
			System.out.println("Starting db Connection..." + dbData.getPort() + dbData.getUser() + dbData.getPass());
			Connection con = DriverManager.getConnection(dbData.getPort(),dbData.getUser(),dbData.getPass());
			System.out.println("Connection succesful!");
			//Writing Query Commands
			//prints out everything in the request database
			PreparedStatement statement = con.prepareStatement("SELECT instructor.faculty_id, instructor.last_name, instructor.full_time, course_name,"
																+ " timeblock.day, timeblock.begin, timeblock.end, timeblock.cs_or_not, choice_order, note" 
																+ " FROM request"
																+ " INNER JOIN instructor"
																+ " ON request.faculty_id = instructor.faculty_id"
																+ " INNER JOIN timeblock"
																+ " ON request.time_id = timeblock.time_id");				
			
			
			//Executing Query Commands
			ResultSet request = statement.executeQuery();
			
			//Request Database
			//puts database information into arraylist
			courses = new ArrayList<Course>();
			while(request.next())
			{
				//Column: Instructor ID, Last Name, FullTime(1) or Adjunct(0), Course Name, Day, Begin, End, Cs(1) or Not(0), Choice Order
				//1 = true, 0 = false
				Instructor prof = new Instructor(request.getString(1), request.getString(2), request.getString(3).equals("0"));
				TimeBlock block = new TimeBlock(request.getString(5), request.getString(6), request.getString(7) );
				//request.getString(8).equals("0") ? "Math" : "CS"
				courses.add( new Course("" , request.getString(4), block, prof, request.getInt(9), request.getString(10)));
	}

			//closes the database
			con.close();
			return courses;
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}
	
	public String[] getInstructorNames(){
		try {
			//Accessing driver from the JAR file
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbData.getPort(),dbData.getUser(),dbData.getPass());
		PreparedStatement statement = con.prepareStatement("SELECT last_name FROM instructor ");	
		ResultSet request = statement.executeQuery();
		
		ArrayList<String> names = new ArrayList<String>();
		while(request.next())
		{
			names.add(request.getString(1));
		}
		con.close();
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(names);
		names.clear();
		names.addAll(hs);
		names.add(0, "");
		
		return Arrays.copyOf(names.toArray(), names.toArray().length, String[].class);
	
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
	
	
	public String[] getInstructorID(){
		try {
			//Accessing driver from the JAR file
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbData.getPort(),dbData.getUser(),dbData.getPass());
		PreparedStatement statement = con.prepareStatement("SELECT faculty_id FROM instructor ");	
		ResultSet request = statement.executeQuery();
		
		ArrayList<String> data = new ArrayList<String>();
		while(request.next())
		{
			data.add(request.getString(1));
		}
		con.close();
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(data);
		data.clear();
		data.addAll(hs);
		data.add(0, "");
		return Arrays.copyOf(data.toArray(), data.toArray().length, String[].class);
	
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
	
	
	public String[] getCourseNames(){
		try {
			//Accessing driver from the JAR file
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbData.getPort(),dbData.getUser(),dbData.getPass());
		PreparedStatement statement = con.prepareStatement("SELECT course_name FROM courses ");	
		ResultSet request = statement.executeQuery();
		
		ArrayList<String> data = new ArrayList<String>();
		while(request.next())
		{
			data.add(request.getString(1));
		}
		con.close();
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(data);
		data.clear();
		data.addAll(hs);
		data.add(0, "");
		return Arrays.copyOf(data.toArray(), data.toArray().length, String[].class);
	
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
	
	public String[] getTimeDays(){
		try {
			//Accessing driver from the JAR file
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbData.getPort(),dbData.getUser(),dbData.getPass());
		PreparedStatement statement = con.prepareStatement("SELECT day FROM timeblock ");	
		ResultSet request = statement.executeQuery();
		
		ArrayList<String> data = new ArrayList<String>();
		while(request.next())
		{
			data.add(request.getString(1));
		}
		con.close();
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(data);
		data.clear();
		data.addAll(hs);
		data.add(0, "");
		return Arrays.copyOf(data.toArray(), data.toArray().length, String[].class);
	
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
	
	public String[] getTimeHours(){
		try {
			//Accessing driver from the JAR file
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbData.getPort(),dbData.getUser(),dbData.getPass());
		PreparedStatement statement = con.prepareStatement("SELECT begin, end FROM timeblock ");	
		ResultSet request = statement.executeQuery();
		
		ArrayList<String> data = new ArrayList<String>();
		while(request.next())
		{
			data.add(request.getString(1) + " - " + request.getString(2));
		}
		con.close();
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(data);
		data.clear();
		data.addAll(hs);
		data.add(0, "");
		return Arrays.copyOf(data.toArray(), data.toArray().length, String[].class);
	
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
}
