<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
</style>

<body>
<div id="indexdiv">
	<form action="verification.jsp?oper=login2" method="post">
		<table>
		<tr><td colspan="2"><input type="text" name="idk" placeholder="用户名" autocomplete="on"></td></tr>
		<tr><td colspan="2"><input type="password" name="psd" placeholder="密码"></td></tr>
		<tr><td><input type="submit" value="登录"></td><td><input type="submit" value="注册" formaction="register.jsp"></td></tr>
		</table>
	</form>
</div>
	
</body>
</html>