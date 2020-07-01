<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.jeetest.dao.*" %>
<%@ page import="com.jeetest.util.*" %>
<%@ page import="java.util.*" %>
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
	<%!ScoreSheet user=new ScoreSheet();
		String sid;%>
	<%
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int credit=0;
		double gp=0;
		ArrayList<ScoreSheet> ss=new ArrayList<ScoreSheet>();
		sid=(String)session.getAttribute("idk");
		try {
			conn=DBUtil.getConnection();
			String sql="select course.cid,cname,credit, grade\r\t"
					+"from course ,choosecourse\r\t"
					+"where sid=? and choosecourse.cid=course.cid and grade is not null";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,sid);
			rs=stmt.executeQuery();
			while(rs.next()) {
				ScoreSheet score=new ScoreSheet();
    			score.setCid(rs.getString(1));
    			score.setCname(rs.getString(2));
    			credit=rs.getInt(3);
    			score.setGrade(rs.getDouble(4));
    			if(score.getGrade()<60) {
    				gp=0;
    				credit=0;
    			}
    			else if(score.getGrade()>=90)
    				gp=4.0;
    			else 
    				gp=(score.getGrade()-60)*0.05+2;
    			score.setCredit(credit);
    			score.setGp(gp);
    			ss.add(score);
			}
		}
		catch(SQLException e){
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
		<tr><th>课程编号</th><th>课程名称</th><th>成绩</th><th>取得学分</th><th>学分绩点</th></tr>
		<%for(int i=0;i<ss.size();i++){ %>
		<tr>
			<td><%=ss.get(i).getCid() %></td>
			<td><%=ss.get(i).getCname() %></td>
			<td><%=ss.get(i).getGrade() %></td>
			<td><%=credit %></td>
			<td><%=gp %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>