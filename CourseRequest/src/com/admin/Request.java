package com.admin;

public class Request {
	private String instructorname, instructorid, fulltime;
	private String course;
	private String day,begin,end;
	
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
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
	public String getFulltime() {
		return fulltime;
	}
	public void setFulltime(String fulltime) {
		this.fulltime = fulltime;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	public Request(String instructorname, String instructorid, String fulltime, String course, String day, String begin, String end){
		this.instructorname = instructorname;
		this.instructorid = instructorname;
		this.fulltime = fulltime;
		this.course = course;
		this.begin = begin;
		this.day = day;
		this.end = end;
	}
}
