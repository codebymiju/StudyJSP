<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test3_result</title>
</head>
<body>
	<h1>test3_result.jsp - JSTL</h1>
	<%-- 변수 num 를 선언 및 10 으로 초기화 --%>
	<c:set var="num" value="10"/>
	<%-- EL 사용하여 변수 num 과 파라미터 name, age 값 출력 --%>
	<h3>파라미터 name = ${param.name }, 파라미터 age = ${param.age }</h3>
	<h3>변수 num = ${num}</h3>
	
	<hr>
	
	<%--
	< JSTL 문법 (조건문, 반복문) >
	[ 조건문 - if ]
	- 기본 문법
	  <c:if test="${조건식}">
	  		// 조건식 판별 결과가 true 일 때 실행할 문장들...
	  </c:if>
    - <c:if> 태그 : if문에 해당하는 커스텀태그(단일 if문과 동일 - else 문 없음)
	 --%>
	 <%--
	 gt : greater than '>'
	 lt : less than '<'
	 ge >= , le <=
	  --%>
	 <c:if test="${num gt 0}"> 
	 	<h3>
	 		\${num} 값이 0보다 크다!<br>
	 		그러므로, 이 메세지는 현재 페이지에서 출력됨!
	 	</h3>
	 </c:if>
	 
	 <c:if test="${empty param.name}">
	 	<h3>
	 		\${param.name} 값이 없습니다!
	 	</h3>
	 </c:if>
	 
	 <hr>
	 <%--
	<c:choose> <c:when> <c:otherwise> 태그
	- if ~ else 문 또는 if ~ else if ~ else 문 등에 해당하는 커스텀태그
	- <c:choose> 태그를 사용하여 조건문이라는 표시를 하고
	  <c:when> 태그에서 조건식을 지정 => 복수개 사용할 경우 다중 else if 문이 됨
	  또한, <c:otherwise> 태그에서 조건식의 판별 결과가 모두 false 일 때 수행할 문장 기술
	  (=  else 문과 동일)
	- 기본 문법
	  <c:choose>
	  	<c:when test="조건식1">
	  		// 조건식1 이 true 일 때 실행할 문장들...
	  	</c:when>
	  	<c:when test="조건식n">
	  		// 조건식n 이 true 일 때 실행할 문장들...
	  	</c:when>
	  	<c:otherwise>
	  		// 조건식이 모두 false 일 때 실행할 문장들...(= else) => 생략 가능
	  	</c:otherwise>
	  </c:choose>
	--%>
	 <c:choose>
	 	<c:when test="${num gt 0}">
	 		<h3>\${num} : ${num}은 양수입니다!</h3>
	 	</c:when>
	 	<c:when test="${num lt 0}">
	 		<h3>\${num} : ${num}은 음수입니다!</h3>
	 	</c:when>
<%-- 	 	<c:when test="${num eq 0}"> --%>
<%-- 	 		<h3>\${num} : ${num}은 0입니다!</h3> --%>
<%-- 	 	</c:when> --%>
		<c:otherwise>
			<h3>\${num} : ${num}은 0입니다!</h3>
		</c:otherwise>
	 </c:choose>
	 
	 <h3>\${param.name} = ${param.name}</h3>
	 
	 <c:choose>
	 	<c:when test="${empty param.name}">
<!-- 	 		<h3>이름 입력 필수!</h3> -->
			<script>
			alert("이름 입력 필수!");
			history.back();
			</script>
	 	</c:when>
	 	<c:when test="${param.name eq '홍길동'}">
	 		<h3>\${param.name}은 홍길동입니다!</h3>
	 	</c:when>
	 	<c:when test="${param.name eq '강감찬'}">
	 		<h3>\${param.name}은 강감찬입니다!</h3>
	 	</c:when>
	 	<c:otherwise>
	 		<h3>\${param.name}은 그 외 나머지입니다!</h3>
	 	</c:otherwise>
	 </c:choose>
</body>
</html>