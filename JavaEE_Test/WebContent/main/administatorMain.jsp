<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-type" content="html/text;charset=utf-8">
<title>Insert title here</title>
</head>
<frameset rows="10%,*,5%" frameborder="1">
	<frame src="top.jsp" name="top1"></frame>
	<frameset cols="15%,*">
		<frame src="administatorLeft.jsp" name="left1"></frame>
		<frame src="right.jsp" name="right1"></frame>
	</frameset>
	<frame src="bottom.jsp" name="bottom1"></frame>
</frameset>

</html>