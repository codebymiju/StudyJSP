<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 내장객체 response를 사용하여 pageContextTest2.jsp 페이지로 이동(=포워딩)
// response.sendRedirect("pageContextTest2.jsp");
pageContext.forward("pageContextTest2.jsp");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 위쪽의 포워딩 작업으로 인하여 실행되지 않는 코드들 -->
	<h1>pageContextTest1.jsp</h1>
	<script type="text/javascript">
	  alert("확인!");
	</script>
</body>
</html>