<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%! String username; %>
	<% 
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf-8");
		username=(String)session.getAttribute("name");
	%>
	<h2>欢迎你!<%=username %></h2>
</body>
</html>