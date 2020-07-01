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
		Teacher teacher=new Teacher();
		String tid;
	%>
	<%	Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		tid=(String)session.getAttribute("idk");
		try {
    		conn=DBUtil.getConnection();
    		String sql="select * from teacher where tid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1,tid);
    		rs=stmt.executeQuery();
    		while(rs.next()){
    			teacher.setTid(rs.getString(1));
    			teacher.setTname(rs.getString(2));
    			teacher.setAge(rs.getInt(3));
    			teacher.setGender(rs.getString(4));
    			teacher.setAcad(rs.getString(5));
    			teacher.setTitle(rs.getString(6));
    			teacher.setHiredate(rs.getDate(7));
    			teacher.setEmial(rs.getString(8));
    			teacher.setPhone(rs.getString(9));
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
	<tr><td>姓名</td><td><%=teacher.getTname() %></td></tr>
	<tr><td>工号</td><td><%=teacher.getTid() %></td></tr>
	<tr><td>年龄</td><td><%=teacher.getAge() %></td></tr>
	<tr><td>性别</td><td><%=teacher.getGender() %></td></tr>
	<tr><td>学院</td><td><%=teacher.getAcad() %></td></tr>
	<tr><td>职称</td><td><%=teacher.getTitle() %></td></tr>
	<tr><td>入职日期</td><td><%=teacher.getHiredate() %></td></tr>
	<tr><td>电子邮箱</td><td><%=teacher.getEmial() %></td></tr>
	<tr><td>电话号码</td><td><%=teacher.getPhone() %></td></tr>
	</table>
</body>
</html>