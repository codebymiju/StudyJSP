<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 처리</h1>
	<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	%>
	<h3>아이디 : <%=id %></h3>
	<h3>패스워드 : <%=passwd %></h3>
	
	<%
// 	if(id == "admin" && passwd == "1234"){ }
	if(id.equals("admin") && passwd.equals("1234")) {
		%>
		<% 
		// 세션 객체에 로그인 성공한 아이디를 "sId"라는 속성명으로 저장하기
		session.setAttribute("sId", id);
		
		// sessionTest3_main.jsp 페이지로 포워딩 (Redirect 방식)
		response.sendRedirect("sessionTest3_main.jsp");
		
	} else {
		%>
		<script>
		alert("로그인 실패!");
		history.back();
		</script>
		<%
	}
		
	%>
</body>
</html>