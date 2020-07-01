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
	<%!ArrayList<ScoreSheet> ss=new ArrayList<ScoreSheet>(); 
		String cid;%>
	<%
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		cid=request.getParameter("carlist");
		try{
			conn=DBUtil.getConnection();
			String sql = "select student.sid,student.sname, grade\r\n"
					+"from student , choosecourse\r\n" 
					+"where student.sid=choosecourse.sid and grade is null and cid=?";
			stmt=conn.prepareStatement(sql);
  			stmt.setString(1,cid);
    		rs=stmt.executeQuery();
    		ss.clear();
    		while(rs.next()){
    			ScoreSheet score=new ScoreSheet();
    			score.setSid(rs.getString(1));
    			score.setSname(rs.getString(2));
    			ss.add(score);
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
	<form action="/JavaEE_Test/TeacherServlet?oper=enterScore&cid=<%=cid %>" method="post">
		<table style="border:1px solid" >
			<tr style="background-color:lightblue"><th>学生学号</th><th>学生姓名</th><th>成绩</th></tr>
			<%for(int i=0;i<ss.size();i++) {%>
			<tr>
				<td><%=ss.get(i).getSid() %></td>
				<td><%=ss.get(i).getSname() %></td>
				<td><input type="number" max="100" min="0" name="<%=ss.get(i).getSid() %>"></td>
			</tr>
				<%} %>
		</table><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>