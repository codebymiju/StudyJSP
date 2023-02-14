<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>requestPro2.jsp - 로그인 처리</h1>
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
		<script>
		alert("로그인 성공!");
		</script>
		<% 
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