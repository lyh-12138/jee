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
<title>Insert title here</title>
</head>
<%String tid;
	String url="#";%>
<%
	Connection conn=null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	tid=(String)session.getAttribute("idk");
	ArrayList<Course> courselist=new ArrayList<Course>();
	if(request.getParameter("oper").equals("checkGrade")){
		url="/JavaEE_Test/TeacherServlet?oper=checkCGrade";
	}
	else if(request.getParameter("oper").equals("enterGrade")){
		url="/JavaEE_Test/teacher/enterStudentGrade.jsp";
	}
	try{
		conn=DBUtil.getConnection();
		String sql ="select cid,cname from course where tid=?";
		stmt=conn.prepareStatement(sql);
		stmt.setString(1,tid);
		rs=stmt.executeQuery();
		courselist.clear();
		while(rs.next()){
			Course course = new Course();
			course.setCid(rs.getString(1));
			course.setCname(rs.getString(2));
			courselist.add(course);
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
<body>
	<form action=<%=url %> method="post">
		<table>
			<tr>
				<td>
					<select name="carlist">
					<%for (int i=0;i<courselist.size();i++){%>
					<option value="<%=courselist.get(i).getCid() %>"><%=courselist.get(i).getCname() %></option>
					<%} %>
					</select>
				</td>
				<td><input type="submit" value="检索"></td>
		</table>
	</form>
</body>
</html>