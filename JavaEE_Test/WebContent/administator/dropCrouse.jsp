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
	<%! Course course=new Course();
		int flag=0;%>
	<%
	Connection conn=null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	flag=0;
	try {
		conn=DBUtil.getConnection();
		String sql="select course.cid,cname,semester,credit,classHour,tname from course,teacher where cid=? and course.tid=teacher.tid";
		stmt=conn.prepareStatement(sql);
		stmt.setString(1,request.getParameter("cid"));
		rs=stmt.executeQuery();
		if(!rs.next()){
			flag=1;
			out.print("<p style=\"font-size:24px\">该课程不存在!</p>");
			response.setHeader("refresh", "1;url=/JavaEE_Test/administator/courseResearch.jsp?oper=dropCourse");
		}
		else{
			out.print("查询到课程：<br><br>");
			course.setCid(rs.getString(1));
			course.setCname(rs.getString(2));
			course.setSemester(rs.getString(3));
			course.setCredit(rs.getInt(4));
			course.setClassHour(rs.getInt(5));
			course.setTname(rs.getString(6));
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
	<%if(1 != flag ){ %>
	<table>
		<tr><td>课程编号</td><td><%=course.getCid() %></td></tr>
		<tr><td>课程名称</td><td><%=course.getCname() %></td></tr>
		<tr><td>开课学期</td><td><%=course.getSemester() %></td></tr>
		<tr><td>学分</td><td><%=course.getCredit() %></td></tr>
		<tr><td>学时</td><td><%=course.getClassHour() %></td></tr>
		<tr><td>任课教师</td><td><%=course.getTname() %></td></tr>
	</table><br><br>
	<form action="/JavaEE_Test/AdministatorServlet?oper=dropCourse&cid=<%=course.getCid() %>" method="post">
		<input type="submit" value="删除">
		<input type="submit" value="取消" formaction="/JavaEE_Test/main/right.jsp">
	</form>
	<%} %>
</body>
</html>