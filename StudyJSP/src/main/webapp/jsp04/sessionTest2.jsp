<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionTest2.jsp</h1>
	<h3><a href="sessionTest2_set.jsp">세션값 생성</a></h3>
	<h3><a href="sessionTest2_remove.jsp">세션값 삭제</a></h3>
	
	<!-- 버튼과 자바스크립트를 조합하여 동일한 작업 수행 -->
    <input type="button" value="세션값 생성" onclick="location.href='sessionTest2_set.jsp'">	
	<input type="button" value="세션값 삭제" onclick="location.href='sessionTest2_remove.jsp'">
	
</body>
</html>