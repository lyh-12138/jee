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
	<%!Teacher user=new Teacher(); %>

	<% 
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn=DBUtil.getConnection();
			String sql="select * from teacher where tid=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setString(1,(String)session.getAttribute("idk"));
    		rs=stmt.executeQuery();
    		while(rs.next()){
    			user.setTid(rs.getString(1));
    			user.setTname(rs.getString(2));
    			user.setAge(rs.getInt(3));
    			user.setGender(rs.getString(4));
    			user.setAcad(rs.getString(5));
    			user.setTitle(rs.getString(6));
    			user.setHiredate(rs.getDate(7));
    			user.setEmial(rs.getString(8));
    			user.setPhone(rs.getString(9));
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
	<form action="/JavaEE_Test/TeacherServlet?oper=modifyInf" method="post">
	<table>
		<tr><td>姓名:</td><td><input type="text" name="tname" value="<%=user.getTname() %>" disabled="disabled"></td><tr>
		<tr><td>工号:</td><td><input type="text" name="tid" value="<%=user.getTid() %>" disabled="disabled"></td></tr>
		<tr><td>性别:</td><td><input type="text" name="gender" value="<%=user.getGender() %>"></td></tr>
		<tr><td>年龄:</td><td><input type="number" name="age" min="1" max="100" step="1" name="age" value="<%=user.getAge() %>"></td></tr>
		<tr><td>学院:</td><td><input type="text" name="acad" value="<%=user.getAcad() %>"></td></tr>
		<tr><td>职称:</td><td><input type="text" name="title" value="<%=user.getTitle() %>"></td></tr>
		<tr><td>入职时间:</td><td><input type="date" name="hiredate" value="<%=user.getHiredate() %>"></td></tr>
		<tr><td>电话号码:</td><td><input type="text" name="phone" value="<%=user.getPhone() %>"></td></tr>
		<tr><td>电子邮件:</td><td><input type="email" name="emial" value="<%=user.getEmial() %>"></td></tr>
	</table><br>
	<input type="submit" value="修改">
	</form>
</body>
</html>