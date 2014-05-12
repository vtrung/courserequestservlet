package com.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.admin.DataConnect;
import com.admin.Request;

public class RequestListBean {
	private ArrayList<String> stringList = new ArrayList<String>();
	private ArrayList<Request> list = new ArrayList<Request>();
	private String test;
	
	public Request getListRequest(int index) {
		return list.get(index);
	}
	
	public ArrayList<Request> getList() {
		return list;
	}

	public void setList(ArrayList<Request> list) {
		this.list = list;
	}

	public String getTest(){
		ResultSet result = DataConnect.getRequestList();
		try {
			while(result.next()){
				//String name = result.getString("last_name");
				//String id = result.getString("faculty_id");
				//String full = result.getString("full_time");
				//String course = result.getString("course_name");
				String time = result.getString("day") + " " + result.getString("begin") + " " + result.getString("end");
				stringList.add(time);
				this.test = stringList.get(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;
	}
	
	public ArrayList<String> getStringList() {
		return stringList;
	}

	public void setStringList(ArrayList<String> stringList) {
		this.stringList = stringList;
	}

	public RequestListBean(){
		ResultSet result = DataConnect.getRequestList();
		String name, id, full;
		String course;
		String day, begin, end;
		String time;
		try {
			while(result.next()){
				name = result.getString("last_name");
				id = result.getString("faculty_id");
				if(result.getString("full_time").equals("1")){
					full = "Full Time";
				}else{
					full = "Adjunct";	
				};
				course = result.getString("course_name");
				day = result.getString("day");
				begin = result.getString("begin");
				end = result.getString("end");
				time = name + " " + id + " " + full + " " + course + " : "
						+ day + " " + begin + " " + end;
				stringList.add(time);
				list.add(new Request(name,id,full,course, day, begin, end));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

