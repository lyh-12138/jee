package com.jeetest.dao;
import java.util.Date;

public class Student {
	private String sid;
	private String sname;
	private int age;
	private String gender;
	private String academy;
	private String classes;
	private Date birthday=new Date();
	private String phone;
	private String email;
	public Student() {
		//todo something...
	}
	public void setSid(String sid) {
		this.sid=sid;
	}
	public void setSname(String sname) {
		this.sname=sname;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setGender(String gender) {
		this.gender=gender;
	}
	public void setAcad(String acad) {
		this.academy=acad;
	}
	public void setClasses(String classes) {
		this.classes=classes;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public void setBirthday(Date birthday) {
		this.birthday=birthday;
	}
	public String getSid() {
		return this.sid;
	}
	public String getSname() {
		return this.sname;
	}
	public int getAge() {
		return this.age;
	}
	public String getGender() {
		return this.gender;
	}
	public String getAcad() {
		return this.academy;
	}
	public String getClasses() {
		return this.classes;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPhone() {
		return this.phone;
	}
	public Date getBirthday() {
		return this.birthday;
	}
}
