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
			<li><a href="/JavaEE_Test/StudentServlet?oper=baseInf" target="right3">基本信息</a></li>
			<li><a href="/JavaEE_Test/student/modifySInf.jsp" target="right3">修改信息</a></li>
			<li><a href="/JavaEE_Test/student/modifySPsd.jsp" target="right3">修改密码</a></li>
			</ul>
		</li>
		<li>选课管理
			<ul>
			<li><a href="/JavaEE_Test/student/chooseCourse.jsp" target="right3">选择课程</a></li>
			<li><a href="/JavaEE_Test/StudentServlet?oper=resultCourse" target="right3">选课结果</a></li>
			<li><a href="/JavaEE_Test/student/dropCourse.jsp" target="right3">退选课程</a></li>
			</ul>
		</li>
		<li>成绩管理
			<ul>
				<li><a href="/JavaEE_Test/StudentServlet?oper=checkGrade" target="right3">成绩查询</a></li>
			</ul>		
	</ul>
</body>
</html>