<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test2.jsp</h1>
	
	<form action="myServlet2" method="get"> <!-- method속성 생략시 디폴트 = get -->
		<input type="submit" value="myServlet2 서블릿 주소 요청(GET)"> 
	</form>
	
	<form action="myServlet2" method="post">
		<input type="submit" value="myServlet2 서블릿 주소 요청(POST)"> 
	</form>
	<!-- myServlet2 요청에 대해 TestMyServlet2 클래스가 실행되도록 매핑 -->
</body>
</html>