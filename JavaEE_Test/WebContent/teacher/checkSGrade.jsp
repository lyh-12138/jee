<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/JavaEE_Test/TeacherServlet?oper=checkSGrade" method="post">
		<table>
			<tr>
				<td><input type="text" name="sid" placeholder="输入学生学号" autocomplete="on"></td>
				<td><input type="submit" value="检索"></td>
		</table>
	</form>
</body>
</html>