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
	   Administator adm=new Administator();
	%>
	<%	Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
    		conn=DBUtil.getConnection();
    		String sql="select * from administator where aid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1,(String)session.getAttribute("idk"));
    		rs=stmt.executeQuery();
    		while(rs.next()){
    			adm.setAid(rs.getString(1));
    			adm.setAname(rs.getString(2));
    			adm.setAge(rs.getInt(3));
    			adm.setGender(rs.getString(4));
    			adm.setBirthday(rs.getDate(5));
    			adm.setEmial(rs.getString(6));
    			adm.setPhone(rs.getString(7));
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
	<tr><td>姓名</td><td><%=adm.getAname() %></td></tr>
	<tr><td>工号</td><td><%=adm.getAid() %></td></tr>
	<tr><td>年龄</td><td><%=adm.getAge() %></td></tr>
	<tr><td>性别</td><td><%=adm.getGender() %></td></tr>
	<tr><td>出生日期</td><td><%=adm.getBirthday() %></td></tr>
	<tr><td>电子邮箱</td><td><%=adm.getEmial() %></td></tr>
	<tr><td>电话号码</td><td><%=adm.getPhone() %></td></tr>
	</table>
</body>
</html>