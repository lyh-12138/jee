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
	<%!Student user=new Student();
		String sid;
		ArrayList <Course> courselist=new ArrayList<Course>();%>
	<%
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		sid=(String)session.getAttribute("idk");
		try{
			conn=DBUtil.getConnection();
    		String sql="select cname,semester,credit,classHour,tname "
    				+"from choosecourse,course,teacher "
    				+"where choosecourse.cid=course.cid and course.tid=teacher.tid and sid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1,sid);
    		rs=stmt.executeQuery();
    		courselist.clear();
    		while(rs.next()){
    			Course course =new Course();
    			course.setCname(rs.getString(1));
    			course.setSemester(rs.getString(2));
    			course.setCredit(rs.getInt(3));
    			course.setClassHour(rs.getInt(4));
    			course.setTname(rs.getString(5));
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
	<table>
		<tr style="background-color:lightblue"><th>课程名称</th><th>开课学期</th><th>课程学时</th><th>课程学分</th><th>任课教师</th>
		<%for(int i=0;i<courselist.size();i++) {%>
		<tr>
			<td><%=courselist.get(i).getCname() %></td>
			<td><%=courselist.get(i).getSemester() %></td>
			<td><%=courselist.get(i).getClassHour() %></td>
			<td><%=courselist.get(i).getCredit() %></td>
			<td><%=courselist.get(i).getTname() %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>