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
	<%! Teacher course=new Teacher(); %>
	<form action="/JavaEE_Test/AdministatorServlet?oper=addTeacher" method="post">
		<table>
		<tr><td>教师工号</td><td><input type="text" name="tid" required="required"></td>
		<tr><td>教师姓名</td><td><input type="text" name="tname" required="required"></td>
		<tr><td>年龄</td><td><input type="text" name="age" ></td>
		<tr><td>性别</td><td><input type="text" name="gender" ></td>
		<tr><td>学院</td><td><input type="text" name="acad" ></td>
		<tr><td>职位</td><td><input type="text" name="title"></td>
		<tr><td>入职时间</td><td><input type="date" name="hiredate"></td>
		<tr><td>电子邮箱</td><td><input type="text" name="emial"></td>
		<tr><td>电话号码</td><td><input type="text" name="phone"></td>
		</table><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>