<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jeetest.dao.*" %>
<%@ page import="com.jeetest.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生成绩</title>
</head>
<style>
table,th,td{
	border:1px solid lightblue;
	border-collapse:collapse;
	text-align:center;
}
</style>
<body>
<%!
	ArrayList<ScoreSheet> ss=new ArrayList<ScoreSheet>();
	double gp=0;
	int credit=0;
	int i=0;
	double mgp=0;
%>
<%
	Connection conn=null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
		conn=DBUtil.getConnection();
		String sql="select choosecourse.sid, sname, choosecourse.cid, cname, credit, grade\r\n" + 
				"from choosecourse, student,course\r\n" + 
				"where choosecourse.sid=student.sid and choosecourse.cid=course.cid and choosecourse.sid=? and grade is not null;";
		stmt=conn.prepareStatement(sql);
		stmt.setString(1,request.getParameter("sid"));
		rs=stmt.executeQuery();
		ss.clear();
		while(rs.next()) {
			ScoreSheet score=new ScoreSheet();
			score.setSid(rs.getString(1));
			score.setSname(rs.getString(2));
			score.setCid(rs.getString(3));
			score.setCname(rs.getString(4));
			credit=rs.getInt(5);
			score.setGrade(rs.getDouble(6));
			if(score.getGrade()<60) {
				gp=0;
				credit=0;
			}
			else if(score.getGrade()>=90){
				gp=4.0;
			}
			else{
				gp=(score.getGrade()-60)*0.05+2;
			}
			score.setCredit(credit);
			score.setGp(gp);
			ss.add(score);
		}
	}catch(SQLException e){
		e.getMessage();
	}
	finally {
		// 清理
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}
%>
<%if (ss.size()>0) {%>
	<h3>查询成功!</h3>
	<p>姓名:<%=ss.get(0).getSname() %></p>
	<p>学号:<%=ss.get(0).getSid() %></p>
	<table>
		<tr style="background-color:lightblue"><th>课程编号</th><th>课程名称</th><th>成绩</th><th>取得学分</th><th>学分绩点</th></tr>
		<%for (i=0;i<ss.size();i++){
			mgp=mgp+ss.get(i).getGp();%>
		<tr>
			<td><%=ss.get(i).getCid() %></td>
			<td><%=ss.get(i).getCname() %></td>
			<td><%=ss.get(i).getGrade() %></td>
			<td><%=ss.get(i).getCredit() %></td>
			<td><%=ss.get(i).getGp() %></td>
		</tr>
		<%} %>
	</table>
	<p>GPA=<%=(mgp/i)%></p>
<% }%>
</body>
</html>