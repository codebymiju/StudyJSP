<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test_redirect_result.jsp</h1>
	
	<!-- 이전 요청에서 전달받은 이름, 나이 파라미터 출력하기 -->
	<!-- 
	redirect 방식은 아래 값이 출력되지 않음! 
	이유 : 새로운 request 객체가 생성되어 이전 데이터가 함께 
	오지 않음
	
	 -->
	<h3>이름 : ${param.name }</h3>
	<h3>나이 : ${param.age }</h3>
</body>
</html>