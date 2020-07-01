<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<style>
</style>
<body>
<div id="indexdiv">
	<form action="verification.jsp?oper=login1" method="post">
		<table>
		<tr><td><input type="text" name="idk" placeholder="用户名" autocomplete="on"></td></tr>
		<tr><td><input type="password" name="psd" placeholder="密码"></td></tr>
		</table>
		<input type="submit" value="登录">
	</form>
</div>
	
</body>
</html>