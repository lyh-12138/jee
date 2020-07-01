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
			<li><a href="/JavaEE_Test/AdministatorServlet?oper=baseInf" target="right1">基本信息</a></li>
			<li><a href="/JavaEE_Test/administator/modifyAInf.jsp" target="right1">修改信息</a></li>
			<li><a href="/JavaEE_Test/administator/modifyAPsd.jsp" target="right1">修改密码</a></li>
			</ul>
		</li>
		<li>课程管理
			<ul>
			<li><a href="/JavaEE_Test/administator/addCourse.jsp" target="right1">增加课程</a></li>
			<li><a href="/JavaEE_Test/administator/courseResearch.jsp?oper=dropCourse" target="right1">删除课程</a></li>
			<li><a href="/JavaEE_Test/administator/courseResearch.jsp?oper=modifyCourse" target="right1">修改课程</a></li>
			<li><a href="/JavaEE_Test/AdministatorServlet?oper=checkCourse" target="right1">查询课程</a></li>
			</ul>
		</li>
		<li>教师管理
			<ul>
			<li><a href="/JavaEE_Test/AdministatorServlet?oper=checkTeacher" target="right1">查询教师</a></li>
			<li><a href="/JavaEE_Test/administator/addTeacher.jsp" target="right1">添加教师</a></li>
			<li><a href="/JavaEE_Test/administator/teacherInput.jsp" target="right1">删除教师</a></li>
			</ul>
		</li>	
		<li>学生管理
			<ul>
			<li><a href="/JavaEE_Test/AdministatorServlet?oper=checkStudent" target="right1">查询学生</a></li>
			<li><a href="/JavaEE_Test/administator/addStudent.jsp" target="right1">添加学生</a></li>
			<li><a href="/JavaEE_Test/administator/studentInput.jsp" target="right1">删除学生</a></li>
			</ul>
		</li>		
	</ul>
</body>
</html>