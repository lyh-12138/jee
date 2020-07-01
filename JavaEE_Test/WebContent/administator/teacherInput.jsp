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
	<form action="/JavaEE_Test/administator/dropTeacher.jsp" method="post">
		<table>
			<tr>
				<td><input type="text" name="tid" placeholder="输入教师工号" required="required"></td>
				<td><input type="submit" value="提交"></td>
		</table>
	</form>
</body>
</html>