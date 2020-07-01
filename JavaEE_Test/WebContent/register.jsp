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
<script type="text/javascript">
	function checkForm(rform){
		if(rform.aid.value=="" || rform.username.value==""){
			alert("工号和姓名不能为空！");
			return false;
		}
		if(rform.psdf.value != rform.psda.value){
			alert("请确保两次输入的密码一致！");
			return false;
		}
		else if (rform.vid.value != "administrator"){
			alert("注册码错误！");
			return false;
		}
		else 
			return true;
		
	}
</script>
<body>
<form name="rform" action="/JavaEE_Test/AdministatorServlet?oper=register" method="post" onsubmit="return checkForm(this)">
	<table>
		<tr><td><label>输入工号</label></td><td><input type="text" name="aid" autocomplete="on"></td></tr>
		<tr><td><label>输入姓名</label></td><td><input type="text" name="username" autocomplete="on"></td></tr>
		<tr><td><label>输入密码</label></td><td><input type="password" name="psdf" autocomplete="on"></td></tr>
		<tr><td><label>再次输入密码</label></td><td><input type="password" name="psda" autocomplete="on"></td></tr>
		<tr><td><label>输入注册码</label></td><td><input type="password" name="vid" autocomplete="on"></td></tr>
	</table><br>
	<input type="submit" value="注册">
</form>
</body>
</html>