package com.jeetest.dao;

import java.util.Date;

public class Teacher {
	private String tid;
	private String tname;
	private int age;
	private String gender;
	private String title;
	private String acad;
	private Date hiredate=new Date();
	private String emial;
	private String phone;
	public Teacher() {
		tid="***";
		tname="***";
		age=0;
		gender="*";
		title="***";
		acad="***";
		emial="***";
		phone="***";
	}
	public void setTid(String tid) {
		this.tid=tid;
	}
	public String getTid() {
		return this.tid;
	}
	public void setTname(String tname) {
		this.tname=tname;
	}
	public String getTname() {
		return this.tname;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public int getAge() {
		return this.age;
	}
	public void setGender(String gender) {
		this.gender=gender;
	}
	public String getGender() {
		return this.gender;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getTitle() {
		return this.title;
	}
	public void setAcad(String acad) {
		this.acad=acad;
	}
	public String getAcad() {
		return this.acad;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate=hiredate;
	}
	public Date getHiredate() {
		return this.hiredate;
	}
	public void setEmial(String emial) {
		this.emial=emial;
	}
	public String getEmial() {
		return this.emial;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public String getPhone() {
		return this.phone;
	}
}
