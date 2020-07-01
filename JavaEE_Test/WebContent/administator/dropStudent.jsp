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
	<%! Student student=new Student();
		int flag=0;%>
	<%
	Connection conn=null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	flag=0;
	try {
		conn=DBUtil.getConnection();
		String sql="select * from student where sid=?";
		stmt=conn.prepareStatement(sql);
		stmt.setString(1,request.getParameter("sid"));
		rs=stmt.executeQuery();
		if(!rs.next()){
			flag=1;
			out.print("<p style=\"font-size:24px\">该学生不存在!</p>");
			response.setHeader("refresh", "1;url=/JavaEE_Test/administator/studentInput.jsp");
		}
		else{
			out.print("查询到学生：<br><br>");
			student.setSid(rs.getString(1));
			student.setSname(rs.getString(2));
			student.setAge(rs.getInt(3));
			student.setGender(rs.getString(4));
			student.setClasses(rs.getString(5));
			student.setEmail(rs.getString(6));
			student.setBirthday(rs.getDate(7));
			student.setAcad(rs.getString(8));
			student.setPhone(rs.getString(9));
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
	<%if(flag!=1){ %>
	<table>
		<tr><td>学号</td><td><%=student.getSid() %></td></tr>
		<tr><td>姓名</td><td><%=student.getSname() %></td></tr>
	</table><br><br>
	<form action="/JavaEE_Test/AdministatorServlet?oper=dropStudent&sid=<%=student.getSid() %>" method="post">
		<input type="submit" value="删除">
		<input type="submit" value="取消" formaction="/JavaEE_Test/main/right.jsp">
	</form>
	<%} %>
</body>
</html>