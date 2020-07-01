<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.jeetest.dao.*" %>
<%@ page import="com.jeetest.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="html/text;charset=utf-8">
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
	<%!Course course=new Course();
		int flag=0;%>

	<% 	
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		flag=0;
		out.print(request.getParameter("cid")+"<br>");
		try{
			conn=DBUtil.getConnection();
			String sql="select * from course where cid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1,request.getParameter("cid"));
    		rs=stmt.executeQuery();
    		if(!rs.next()){
    			flag=1;
    			out.print("<p style=\"font-size:24px\">该课程不存在!</p>");
    			response.setHeader("refresh", "1;url=/JavaEE_Test/administator/courseResearch.jsp?oper=modifyCourse");
    		}
    		else{
   				course.setCid(rs.getString(1));
   				course.setCname(rs.getString(2));
   				course.setSemester(rs.getString(3));
   				course.setCredit(rs.getInt(4));
   				course.setClassHour(rs.getInt(5));
   				course.setTid(rs.getString(6));
    		}
		}catch (SQLException e) {
			e.getMessage();
		} finally {
			// 清理
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.getStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.getStackTrace();
				}
			}
		}
	%>
	out.print(<%=flag %>);
	<%if(flag!=1){ %>
	<form action="/JavaEE_Test/AdministatorServlet?oper=modifyCourse&cid=<%=course.getCid() %>" method="post">
	<table>
		<tr><td>课程编号:</td><td><input type="text" name="cid" value="<%=course.getCid() %>" disabled="disabled"></td></tr>
		<tr><td>课程名称:</td><td><input type="text" name="cname" value="<%=course.getCname() %>" required="required"></td><tr>
		<tr><td>开课学期:</td><td><input type="text" name="semester" value="<%=course.getSemester() %>"></td></tr>
		<tr><td>学分:</td><td><input type="number" name="credit" min="0" max="10" step="1" name="credit" value="<%=course.getCredit() %>"></td></tr>
		<tr><td>学时:</td><td><input type="number" name="classHour" min="1" max="16" value="<%=course.getClassHour() %>"></td></tr>
		<tr><td>任课教师工号:</td><td><input type="text" name="tid" value="<%=course.getTid() %>" required="required"></td></tr>
	</table><br>
	<input type="submit" value="修改">
	</form>
	<%} %>
</body>
</html>