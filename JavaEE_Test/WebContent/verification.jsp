<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.jeetest.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<style>
</style>

<body>
	<%!String username;
	   String password;
	   int flag=-1;
	   String name;
	   String login;
	%>
	<%
		username=request.getParameter("idk");
		password=request.getParameter("psd");
		login=request.getParameter("oper");
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn=DBUtil.getConnection();
			String sql ="select * from verification where idk=? and passwords=?";
			stmt=conn.prepareStatement(sql);
    		stmt.setString(1,username);
    		stmt.setString(2,password);
    		rs=stmt.executeQuery();
    		if(rs.next()){
    			name=rs.getString(2);
    			flag=rs.getInt(4);
	    		if(flag==1){
					out.println("管理员登录！登陆成功，即将跳转到主页面...");
					session.setAttribute("name", name);
					session.setAttribute("idk", username);
	    			response.setHeader("refresh", "1;url='main/administatorMain.jsp'");
				}
				else if (flag==2){
					out.println("教师登录！登陆成功，即将跳转到主页面...");
					session.setAttribute("name", name);
					session.setAttribute("idk", username);
	    			response.setHeader("refresh", "1;url='main/teacherMain.jsp'");
				}
				else if (flag==3){
					out.println("学生登录！登陆成功，即将跳转到主页面...");
					session.setAttribute("name", name);
					session.setAttribute("idk", username);
	    			response.setHeader("refresh", "1;url='main/studentMain.jsp'");
				}
    		}
			else{
				out.println("账号或密码错误!登陆失败...");
				if(login.equals("login1"))
    				response.setHeader("refresh", "1;url='login1.jsp'");
				else
					response.setHeader("refresh", "1;url='login2.jsp'");
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
</body>
</html>