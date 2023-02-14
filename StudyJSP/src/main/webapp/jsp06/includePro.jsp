<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//현재 페이지(includePro.jsp)는 includeForm.jsp 페이지에 포함(삽입)될 페이지이며
//include 액션태그 사용 시 param 태그로 전달한 파라미터를 가져와서 사용 가능함
String paramValue = request.getParameter("paramValue");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>includePro.jsp</h1>
	<h3>include 액션태그에 의해 포함되는 페이지입니다.</h3>
	<h3>전달받은 파라미터값 : <%=paramValue %></h3>
	
</body>
</html>