<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<%!String url; %>
<%
	String oper=request.getParameter("oper");
	if(oper==null)oper="error";
	switch(oper){
	case "dropCourse":
		url="/JavaEE_Test/administator/dropCrouse.jsp";
		break;
	case "modifyCourse":
		url="/JavaEE_Test/administator/modifyCourse.jsp";
		break;
	default:
		out.print("出现了一些错误<br>");
		url="";
		break;
	}
%>
<body>
	<form action=<%=url %> method="post">
		<table>
			<tr>
				<td><input type="text" name="cid" placeholder="输入课程编号" required="required"></td>
				<td><input type="submit" value="提交"></td>
		</table>
	</form>
</body>
</html>