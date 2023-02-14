<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>applicationTest.jsp</h1>
	<h3>서버 정보 : <%=application.getServerInfo() %></h3>
	<h3>서버 루트의 물리적 경로 정보 : <%=application.getRealPath("/") %></h3>
	<h3>서버 컨텍스트(프로젝트) 경로 정보 : <%=application.getContextPath() %></h3>
</body>
</html>