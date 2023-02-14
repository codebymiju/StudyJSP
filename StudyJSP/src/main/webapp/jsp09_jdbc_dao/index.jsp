<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<style type="text/css">
	header {
		width : 800x;
		height : 80px;
		text-align: right;
	}
	
	article {
		width : 1024px;
		height : 600px;
		text-align: center;
		border: 1px solid blue;
	}
	
	footer {
		width : 800x;
		height : 100px;
		text-align: center;
	
	}
	
</style>
</head>
<body>
	<%
	// 세션 객체에 저장된 세션아이디(속성명 "sId")가져와서 변수 id에 저장
	String id = (String)session.getAttribute("sId");
	%>
	<header>
		<!-- 세션 아이디 존재 여부에 따라 다른 링크 표시 -->
		<%if(id == null || id.equals("")) { %>
			<h5><a href = "loginForm2.jsp">로그인</a> | <a href = "insertForm2.jsp">회원가입</a></h5>
		<% } else { %>
			<!-- 또한, 로그인 된 아이디(=세션 아이디)가 "admin"이면 관리자 페이지 링크 추가 -->
			<h5>
				<a href = "select2_detail.jsp?id=<%=id%>"><%=id%> 님</a> | 
				<a href = "logout.jsp">로그아웃</a> |
				<%
				if(id.equals("admin")) {
					%>
					<a href ="select2.jsp">관리자페이지</a>
					<% 
				}
				%>			
			</h5>
		<% } %>
	</header>
	<article>
		<h1>index.jsp</h1>
	</article>
	<footer>
		<h5>아이티윌 부산교육센터 오시는 길</h5>
	</footer>
</body>
</html>