<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionTest1.jsp</h1>
	
	<!-- 세션 유지시간(maxInactiveInterval) 값 확인 -->
	<h3>현재 세션 유지시간 : <%=session.getMaxInactiveInterval() %> 초</h3>
	
	<!-- 세션 유지시간 10초로 변경 -->
	<%session.setMaxInactiveInterval(10); %> <!-- 스크립틀릿이라 ; 필요 -->
	<h3>변경 후 세션 유지시간 : <%=session.getMaxInactiveInterval() %> 초</h3>
	
	<h3>새 세션 여부 : <%=session.isNew() %></h3>
	<h3>세션 아이디 : <%=session.getId() %></h3>
	<h3>세션 생성 시각 : <%=new Date(session.getCreationTime()) %></h3>
	<h3>세션 마지막 접근 시각 : <%=new Date(session.getLastAccessedTime()) %></h3>
	
	<hr>
	<!-- 세션 강제 초기화(= 세션 객체를 제거) -->
	<%session.invalidate(); %>
	<h3>새 세션 여부 : <%=session.isNew() %></h3>
</body>
</html>