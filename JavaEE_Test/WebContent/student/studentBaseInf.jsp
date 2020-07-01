<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.jeetest.dao.*" %>
<%@ page import="com.jeetest.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
table,th,td{
	border:1px solid lightblue;
	border-collapse:collapse;
	text-align:center;
}
</style>
<body>
	<%!String username;
		Student stu=new Student();
	%>
	<%	Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
    		conn=DBUtil.getConnection();
    		String sql="select * from student where sid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1,(String)session.getAttribute("idk"));
    		rs=stmt.executeQuery();
    		while(rs.next()){
    			stu.setSid(rs.getString(1));
    			stu.setSname(rs.getString(2));
    			stu.setAge(rs.getInt(3));
    			stu.setGender(rs.getString(4));
    			stu.setClasses(rs.getString(5));
    			stu.setEmail(rs.getString(6));
    			stu.setBirthday(rs.getDate(7));
    			stu.setAcad(rs.getString(8));
    			stu.setPhone(rs.getString(9));
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
	<table>
	<tr><td>姓名</td><td><%=stu.getSname() %></td></tr>
	<tr><td>学号</td><td><%=stu.getSid() %></td></tr>
	<tr><td>年龄</td><td><%=stu.getAge() %></td></tr>
	<tr><td>性别</td><td><%=stu.getGender() %></td></tr>
	<tr><td>学院</td><td><%=stu.getAcad() %></td></tr>
	<tr><td>班级</td><td><%=stu.getClasses() %></td></tr>
	<tr><td>出生日期</td><td><%=stu.getBirthday() %></td></tr>
	<tr><td>电子邮箱</td><td><%=stu.getEmail() %></td></tr>
	<tr><td>电话号码</td><td><%=stu.getPhone() %></td></tr>
	</table>
</body>
</html>