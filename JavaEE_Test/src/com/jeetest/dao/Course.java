package com.jeetest.dao;

public class Course {
	private String cid;
	private String cname;
	private String semester;
	private int credit;
	private int classHour;
	private String tid;
	private String tname;
	public Course() {
		cid="***";
		cname="***";
		semester="***";
		credit=0;
		classHour=0;
		tid="***";
		tname="***";
	}
	public void setCid(String cid) {
		this.cid=cid;
	}
	public void setCname(String cname) {
		this.cname=cname;
	}
	public void setSemester(String semester) {
		this.semester=semester;
	}
	public void setCredit(int  credit) {
		this.credit=credit;
	}
	public void setClassHour(int classHour) {
		this.classHour=classHour;
	}
	public void setTid(String tid) {
		this.tid=tid;
	}
	public void setTname(String tname) {
		this.tname=tname;
	}
	public String getCid() {
		return this.cid;
	}
	public String getCname() {
		return this.cname;
	}
	public String getSemester() {
		return this.semester;
	}
	public int getCredit() {
		return this.credit;
	}
	public int getClassHour() {
		return this.classHour;
	}
	public String getTid() {
		return this.tid;
	}
	public String getTname() {
		return this.tname;
	}
}
