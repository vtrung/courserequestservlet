package com.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.admin.DataConnect;

public class CourseListBean {
	private ArrayList<String> list = new ArrayList<String>();
	
	public CourseListBean(){
		ResultSet rs = DataConnect.runQuery("SELECT * FROM schedule.courses");
		try {
			System.out.println(rs.getFetchSize());
			while(rs.next()){
				list.add(rs.getString("course_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed CourseBean");
			e.printStackTrace();
		}
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	
}
