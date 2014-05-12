package com.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.admin.DataConnect;

public class TimeListBean {
	
	private ArrayList<String> time = new ArrayList<String>();
	
	public TimeListBean(){
		ResultSet rs = DataConnect.getTimeList();
		try {
			while(rs.next()){
				time.add(rs.getString("day") + "," + rs.getString("begin") + "," + rs.getString("end"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for(int index = 0; index < times.length; index++){
			time.add(times[index]);
		}*/
	}

	public ArrayList<String> getTime() {
		return time;
	}

	public void setTime(ArrayList<String> time) {
		this.time = time;
	}
	
}
