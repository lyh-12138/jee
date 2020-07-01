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
	<%! Student course=new Student(); %>
	<form action="/JavaEE_Test/AdministatorServlet?oper=addStudent" method="post">
		<table>
		<tr><td>学生学号</td><td><input type="text" name="sid" required="required"></td>
		<tr><td>学生姓名</td><td><input type="text" name="sname" required="required"></td>
		<tr><td>年龄</td><td><input type="text" name="age" ></td>
		<tr><td>性别</td><td><input type="text" name="gender" ></td>
		<tr><td>出生日期</td><td><input type="date" name="birthday"></td>
		<tr><td>学院</td><td><input type="text" name="acad" ></td>
		<tr><td>班级</td><td><input type="text" name="classes"></td>
		<tr><td>电子邮箱</td><td><input type="text" name="emial"></td>
		<tr><td>电话号码</td><td><input type="text" name="phone"></td>
		</table><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>