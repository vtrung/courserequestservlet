package com.request;

import java.sql.Time;
import java.util.ArrayList;

import com.admin.Request;

public class ActiveListBean {
	private RequestListBean requests = new RequestListBean();
	private ArrayList<Request> list = new ArrayList<Request>();
	
	
	public ArrayList<Request> getList() {
		return list;
	}

	public void setList(ArrayList<Request> list) {
		this.list = list;
	}

	public String addList(){
		Time date = new Time(1000);
		System.out.println("addList()" + date);
		this.list.add(requests.getListRequest(1));
		return "Successfully Added";
	}
	
	public ActiveListBean(){
	}
}
