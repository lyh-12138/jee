<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>个人管理
			<ul>
			<li><a href="/JavaEE_Test/TeacherServlet?oper=baseInf" target="right2">基本信息</a></li>
			<li><a href="/JavaEE_Test/teacher/modifyTInf.jsp" target="right2">修改信息</a></li>
			<li><a href="/JavaEE_Test/teacher/modifyTPsd.jsp" target="right2">修改密码</a></li>
			</ul>
		</li>
		<li>成绩管理
			<ul>
			<li><a href="/JavaEE_Test/teacher/checkSGrade.jsp" target="right2">查看学生成绩</a></li>
			<li><a href="/JavaEE_Test/teacher/courseResearch.jsp?oper=checkGrade" target="right2">查看课程成绩</a></li>
			<li><a href="/JavaEE_Test/teacher/courseResearch.jsp?oper=enterGrade" target="right2">录入学生成绩</a></li>
			</ul>
		</li>		
	</ul>
</body>
</html>