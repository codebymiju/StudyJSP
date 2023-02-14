<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>영역 객체의 속성 및 범위</h1>
	<%
	// 4대 영역 : page, request, session, application
	// 4대 영역 객체 : pageContext, request, session, application
	// 각 영역 객체에 속성값 저장 => setAttribute(String name, Object value)
	pageContext.setAttribute("pageValue", "pageContext value");
	request.setAttribute("requestValue", "request value");
	session.setAttribute("sessionValue", "session value");
	application.setAttribute("applicationValue", "application value");
	%>
	
	<!-- 영역 객체에 속성값을 저장한 페이지에서 저장된 값 확인하기 -->
	<!-- getAttribute(String name) -->
	<h3>pageContext 객체 값 : <%= pageContext.getAttribute("pageValue") %></h3>
	<h3>request 객체 값 : <%= request.getAttribute("requestValue") %></h3>
	<h3>session 객체 값 : <%= session.getAttribute("sessionValue") %></h3>
	<h3>application 객체 값 : <%= application.getAttribute("applicationValue") %></h3>
	
	<%
	// 1. Redirect 방식 포워딩
// 	response.sendRedirect("attributeScopeTest2.jsp");
	
	// 2. Dispatch 방식 포워딩
	pageContext.forward("attributeScopeTest2.jsp");
	%>
</body>
</html>