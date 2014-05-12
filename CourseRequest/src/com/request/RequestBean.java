package com.request;

import com.admin.DataConnect;

public class RequestBean {
	private String instructorname, instructorid;
	private String course1, course2, course3, course4;
	private String time1, time2, time3, time4;
	private int timeid1, timeid2, timeid3, timeid4;
	private String fulltime;
	private String note1 = "";
	private String note2 = "";
	private String note3 = "";
	private String note4 = "";
	
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	public String getNote3() {
		return note3;
	}
	public void setNote3(String note3) {
		this.note3 = note3;
	}
	public String getNote4() {
		return note4;
	}
	public void setNote4(String note4) {
		this.note4 = note4;
	}
	public String getFulltime() {
		return fulltime;
	}
	public void setFulltime(String fulltime) {
		this.fulltime = fulltime;
	}
	public String getInstructorname() {
		return instructorname;
	}
	public void setInstructorname(String instructorname) {
		this.instructorname = instructorname;
	}
	public String getInstructorid() {
		return instructorid;
	}
	public void setInstructorid(String instructorid) {
		this.instructorid = instructorid;
	}
	public String getCourse1() {
		return course1;
	}
	public void setCourse1(String course1) {
		this.course1 = course1;
	}
	public String getCourse2() {
		return course2;
	}
	public void setCourse2(String course2) {
		this.course2 = course2;
	}
	public String getCourse3() {
		return course3;
	}
	public void setCourse3(String course3) {
		this.course3 = course3;
	}
	public void setCourse4(String course4) {
		this.course4 = course4;
	}
	public String getCourse4() {
		return course4;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getTime3() {
		return time3;
	}
	public void setTime3(String time3) {
		this.time3 = time3;
	}
	public String getTime4() {
		return time4;
	}
	public void setTime4(String time4) {
		this.time4 = time4;
	}
	
	public void convertTime(){
		timeid1 = DataConnect.getTimeID(time1);
		timeid2 = DataConnect.getTimeID(time2);
		timeid3 = DataConnect.getTimeID(time3);
		timeid4 = DataConnect.getTimeID(time4);
	}
	
	public String build(){
		if(fulltime.equals("FullTime") ){
			DataConnect.insertInstructor(instructorid, instructorname,"1");
		} else {
			DataConnect.insertInstructor(instructorid, instructorname,"0");
		}
		convertTime();

		System.out.println("insert() start");
		DataConnect.runUpdate("INSERT INTO schedule.request VALUES (NULL,'"+instructorid+"','"+course1+"','"+timeid1+"','1','"+note1+"');");
		if(!course2.equals("none"))
			DataConnect.runUpdate("INSERT INTO schedule.request VALUES (NULL,'"+instructorid+"','"+course2+"','"+timeid2+"','2','"+note2+"');");
		if(!course3.equals("none"))
			DataConnect.runUpdate("INSERT INTO schedule.request VALUES (NULL,'"+instructorid+"','"+course3+"','"+timeid3+"','3','"+note3+"');");
		if(!course4.equals("none"))
			DataConnect.runUpdate("INSERT INTO schedule.request VALUES (NULL,'"+instructorid+"','"+course4+"','"+timeid4+"','4','"+note4+"');");
		return "Your Request was successfully submitted";
	}
}
