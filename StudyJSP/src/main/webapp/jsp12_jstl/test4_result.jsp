<%@page import="java.util.List"%>
<%@page import="jsp11_dbcp.StudentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test4_result.jsp</title>
</head>
<body>
<%--
	request 객체의 속성값을 가져올 때 request.getAttribute() 메서드를 사용했으며,
	EL 문법을 통해 가져올 때 ${속성명} 형식을 사용하여 데이터 꺼낼 수 있다!
	=> page, request, session, application 영역을 차례대로 검사하여 일치하는 속성을 꺼낸다!
	

	form태그나 주소값에 붙여서 넘겼다면 EL에서 param.으로 작성
	setAttribute로 넣었던 객체들은 객체명만 작성하면됨! 
--%>
	<h1>test4_result.jsp - JSTL</h1>
	<h3>
		번호 : ${idx}<br>
		이름 : ${name}
	</h3>
	<hr>
	<%-- 스크립틀릿을 활용하여 일반적인 for문을 통해 i값이 1 ~ 10까지 1씩 증가하면서 반복 --%>
	<%for(int i = 1; i <= 10; i++) { %>
		<%=i%>&nbsp;
	<%}%>
	<hr>
	<%--
	JSTL 의 <c:forEach> 태그를 활용한 반복문
	
	1) 제어변수를 활용하여 시작값 ~ 종료값까지 증감값만큼 증감을 통해 반복하는 문장
	<c:forEach var="변수명" begin="시작값" end="종료값" step="증감값">
		// 반복문 내에서 실행할 문장들...
	</c:forEach>
	--%>
	<c:forEach var="i" begin="1" end="10" step="1">
		<%-- 제어변수로 사용되는 i 값을 EL 을 통해 사용 가능 --%>
		${i}&nbsp;
	</c:forEach>
	<hr>
	<%--
	2) 객체에 접근하여 객체 내의 데이터를 차례대로 접근하는 forEach문(= 향상된 for문)
	<c:forEach var="객체 데이터 저장 변수" items="복수개 데이터 저장된 객체" varStatus="상태변수">
	</c:forEach>
	--%>
	<%-- names 배열에 접근하여 이름을 차례대로 출력 --%>
	<c:forEach var="item" items="${names}" varStatus="status">
		${status.index} 번 배열 데이터 : ${item }<br>
	</c:forEach>	
	<hr>
	<%-- List 객체(studentList) 에 저장된 StudentDTO 객체를 차례대로 접근 --%>
	<%
	List<StudentDTO> studentList = (List<StudentDTO>)request.getAttribute("studentList");
	for(StudentDTO student : studentList) { %>
		<%=student.getIdx()%>. <%=student.getName()%><br>
	<% } %>
	<hr>
	<%-- <c:forEach> 문 사용 --%>
	<c:forEach var="student" items="${studentList}">
		${student.idx}. ${student.name}<br>
	</c:forEach>
</body>
</html>