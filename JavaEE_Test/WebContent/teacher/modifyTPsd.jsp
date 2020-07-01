<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="html/text;charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/JavaEE_Test/TeacherServlet?oper=modifyPsd" method="post">
		<table>
			<tr><td>输入旧密码:</td><td><input type="password" name="lpsd" required="required"></td></tr>
			<tr><td>输入新密码:</td><td><input type="password" name="npsd" required="required"></td></tr>
		</table>
	<input type="submit" value="修改" >
	<input type="submit" value="取消" formaction="/JavaEE_Test/main/right.jsp" formnovalidate="formnovalidate">
	</form>
</body>
</html>