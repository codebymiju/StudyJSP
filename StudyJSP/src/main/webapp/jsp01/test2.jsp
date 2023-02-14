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
	<!-- HTML 주석입니다. 이 주석은 웹브라우저 소스보기를 통해 확인이 가능합니다. -->
	<h1>test2.jsp</h1>
	<%--
	JSP 주석입니다. 이 주석은 웹브라우저 소스보기를 통해 확인이 불가능합니다.
	-> JSP 코드가 포함될 경우 HTML 주석으로는 처리가 불가능하므로
	   반드시 JSP 주석을 사용해야한다!
	--%>
	<%
	// 이 부분은 JSP 문서 내에서 자바 코드가 기술되는 부분으로
	// 웹브라우저에서 코드가 표시되지 않으며
	// 서버측에서 실행된 결과값만 전달되는 부분입니다.
	// 따라서, 자바에서 사용하는 주석도 사용 가능합니다.
	Date now = new Date();
	%>
	<h3>현재 시간 : <%=now %></h3>
	
	<hr>
	
	<!--HTML 태그는 HTML 주석으로 처리하여, 실행 대상에서 제외 가능 -->
<!-- 	<h1>test2.jsp</h1> -->

	<!-- JSP 코드 부분은 HTML 주석으로 처리하더라도, 
	     서버에서 이미 실행된 상태로 전송됨 -->
	<!-- <h3>현재 시간 : <%=now %></h3> -->
	
	<%-- JSP 주석은 서버에서 해당 주석 실행하지 않고, 브라우저로 전송도 안함 --%>
<%-- 	<h3>현재 시간 : <%=now %></h3>  --%>
</body>
</html>