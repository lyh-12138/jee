package com.jeetest.dao;

public class ScoreSheet {
	private String sid;
	private String sname;
	private String cid;
	private String cname;
	private String acad;
	private String classes;
	private int credit;
	private double grade;
	private double gp;
	public ScoreSheet() {
		sid="***";
		sname="***";
		cid="***";
		cname="***";
		acad="***";
		classes="***";
		credit=0;
		grade=0;
		gp=0;
	}
	public void setSid(String sid) {
		this.sid=sid;
	}
	public String getSid() {
		return this.sid;
	}
	public void setSname(String sname) {
		this.sname=sname;
	}
	public String getSname() {
		return this.sname;
	}
	public void setCid(String cid) {
		this.cid=cid;
	}
	public String getCid() {
		return this.cid;
	}
	public void setCname(String cname) {
		this.cname=cname;
	}
	public String getCname() {
		return this.cname;
	}
	public void setAcad(String acad) {
		this.acad=acad;
	}
	public String getAcad() {
		return this.acad;
	}
	public void setClasses(String classes) {
		this.classes=classes;
	}
	public String getClasses() {
		return this.classes;
	}
	public void setCredit(int credit) {
		this.credit=credit;
	}
	public int getCredit() {
		return this.credit;
	}
	public void setGrade(double grade) {
		this.grade=grade;
	}
	public double getGrade() {
		return this.grade;
	}
	public void setGp(double gp) {
		this.gp=gp;
	}
	public double getGp() {
		return this.gp;
	}
}
