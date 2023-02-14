<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test_dispatch_result.jsp</h1>
	
	<!-- 이전 요청에서 전달받은 이름, 나이 파라미터 출력하기 -->
	<h3>이름 : ${param.name }</h3>
	<h3>나이 : ${param.age }</h3>
	<!-- 
	11/25
	Dispatch 방식으로 포워딩했으므로 이전 request 객체가 유지되어
	새로 포워딩 된 현재 페이지에서도 request 객체를 통해 파라미터 값에 접근 가능하다!
	 -->
</body>
</html>