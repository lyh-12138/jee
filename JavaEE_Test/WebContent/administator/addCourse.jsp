<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.jeetest.dao.*" %>
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
	<%! Course course=new Course(); %>
	<form action="/JavaEE_Test/AdministatorServlet?oper=addCourse" method="post">
		<table>
		<tr><td>课程编号</td><td><input type="text" name="cid" required="required"></td>
		<tr><td>课程名称</td><td><input type="text" name="cname" required="required"></td>
		<tr><td>开课时间</td><td><input type="text" name="semester" placeholder="yyyy-yyyy-s"></td>
		<tr><td>学分</td><td><input type="text" name="credit" ></td>
		<tr><td>学时</td><td><input type="text" name="classhour" ></td>
		<tr><td>任课教师编号</td><td><input type="text" name="tid" required="required"></td>
		</table><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>