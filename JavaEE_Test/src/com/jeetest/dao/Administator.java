package com.jeetest.dao;

import java.util.Date;

public class Administator {
	private String aid;
	private String aname;
	private int age;
	private String gender;
	private Date birthday=new Date();
	private String emial;
	private String phone;
	public Administator() {
		aid="***";
		aname="***";
		age=0;
		gender="*";
		emial="***";
		phone="***";
	}
	public void setAid(String aid) {
		this.aid=aid;
	}
	public String getAid() {
		return this.aid;
	}
	public void setAname(String aname) {
		this.aname=aname;
	}
	public String getAname() {
		return this.aname;
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
	public void setBirthday(Date birthday) {
		this.birthday=birthday;
	}
	public Date getBirthday() {
		return this.birthday;
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
