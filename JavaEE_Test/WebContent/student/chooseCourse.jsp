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
		String semester="2019-2020-2";
		String cid;
		String sid;
		ArrayList <Course> courselist=new ArrayList<Course>();%>
	<%
		/*java.util.Date date=new java.util.Date();
		java.util.Date dateStart=new java.util.Date();
		java.util.Date dateEnd=new java.util.Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		dateStart =sdf.parse("2019-08-20");
		dateEnd=sdf.parse("2020-01-20");
		if(date.after(dateStart) && date.before(dateEnd)){
			semester="2019-2020-2";
		}
		else if(date.before(dateStart)){
			semester="2019-2020-1";
		}
		else{
			semester="2020-2021-1";
		}*/
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		sid=(String)session.getAttribute("idk");
		try{
			conn=DBUtil.getConnection();
			String sql = "select * from course where semester=? and  not exists"+
						"(select * from choosecourse where course.cid=choosecourse.cid and sid=?)";
			stmt=conn.prepareStatement(sql);
    		stmt.setString(1,semester);
    		stmt.setString(2, sid);
    		rs=stmt.executeQuery();
    		courselist.clear();
    		while(rs.next()){
    			Course course =new Course();
    			course.setCid(rs.getString(1));
    			course.setCname(rs.getString(2));
    			course.setCredit(rs.getInt(4));
    			course.setClassHour(rs.getInt(5));
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
	<form method="post">
		<table " >
			<tr style="background-color:lightblue"><th>课程编号</th><th>课程名称</th><th>课程学时</th><th>课程学分</th><th>选择</th>
			<%for(int i=0;i<courselist.size();i++) {%>
			<tr>
				<td><%=courselist.get(i).getCid() %></td>
				<td><%=courselist.get(i).getCname() %></td>
				<td><%=courselist.get(i).getClassHour() %></td>
				<td><%=courselist.get(i).getCredit() %></td>
				<td><input type="submit" value="选择" formaction="/JavaEE_Test/StudentServlet?oper=chooseCourse&cid=<%=courselist.get(i).getCid()%>"></td>
			</tr>
				<%} %>
		</table>
	</form>
</body>
</html>