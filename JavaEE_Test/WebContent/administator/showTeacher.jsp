<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jeetest.dao.*" %>
<%@ page import="com.jeetest.util.*" %>
<%@ page import="com.jeetest.bean.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页示例</title>
</head>
<style>
table,th,td{
	border:1px solid lightblue;
	border-collapse:collapse;
	text-align:center;
}
</style>
<body>
<%! String pageNumber="";
	int number;
	String cid;
	int start;
	int length;	
%>
<%
	pageNumber = request.getParameter("pageNumber"); //显示第几页
	number = Integer.parseInt(pageNumber);
	length = 20; //每页显示的数据条数
	start = 0; //开始的记录序号
	start = (number - 1) * length;
	PagedTeacherSheet ps = new PagedTeacherSheet();
	List<Teacher> teachers = ps.getTeacherList(start, length);
	out.print("<h3>查询成功!</h3>");
	int count = ps.totalCount(); //总条数
	int total = count / length; //总页数
	out.print("<p>共检索到记录：<span>"+count+"</span>条<br>");
	// 调整总页数
	if (count % length != 0) {
		total++;
	}
%>
	<table width='80%'>
		<tr style="background-color:lightblue">
			<th>编号</th><th >名字</th><th>年龄</th><th>性别</th><th>学院</th><th>职称</th><th>入职时间</th><th>邮箱</th><th>电话</th>
		</tr>
		<%for (Teacher ss : teachers) {%>
			<tr>
				<td><%=ss.getTid()%></td>
				<td><%=ss.getTname()%></td>
				<td><%=ss.getAge()%></td>
				<td><%=ss.getGender()%></td>
				<td><%=ss.getAcad() %></td>
				<td><%=ss.getTitle() %></td>
				<td><%=ss.getHiredate() %></td>
				<td><%=ss.getEmial() %></td>
				<td><%=ss.getPhone() %></td>
			</tr>
		<%}%>
	</table>
	<br /> 页码：
	<%for (int i = 1; i <= total; i++) {%>
	<a href="/JavaEE_Test/administator/showTeacher.jsp?pageNumber=<%=i%>"><%=i%></a>&nbsp;&nbsp;
	<%}%>

</body>
</html>