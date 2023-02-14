<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test6_redirect_dispatch.jsp</h1>
	<form action="redirectServlet2" method="get">
		이름 : <input type="text" name="name"><br>
		나이 : <input type="text" name="age"><br> 
		<input type="submit" value="redirectServlet 서블릿 주소 요청(GET)"> 
	</form>
	<hr>
	<form action="dispatchServlet2" method="get">
		이름 : <input type="text" name="name"><br>
		나이 : <input type="text" name="age"><br> 
		<input type="submit" value="dispatchServlet 서블릿 주소 요청(POST)"> 
	</form>
</body>
</html>