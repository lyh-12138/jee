<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<%!String url; %>

<body>
	<form action="/JavaEE_Test/administator/dropStudent.jsp" method="post">
		<table>
			<tr>
				<td><input type="text" name="sid" placeholder="输入学生学号" required="required"></td>
				<td><input type="submit" value="提交"></td>
		</table>
	</form>
</body>
</html>