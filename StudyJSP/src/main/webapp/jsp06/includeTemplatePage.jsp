<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>include 템플릿 페이지</h1>
	<table border="1">
		<tr>
			<td colspan="2" width="800" height="100">
			<jsp:include page="includeTemplate_top.jsp"></jsp:include>
			
			</td>
		</tr>
		<tr>
			<td width="200" height="400">
			<jsp:include page="includeTemplate_left.jsp"></jsp:include>
			</td>
			<td>
			 	<h1 align="center">메인화면</h1>
			 	<!-- includeTemplate_main.jsp 페이지 포함시키기 -->
			 	<jsp:include page="includeTemplate_main.jsp"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="800" height="100">
			<jsp:include page="includeTemplate_bottom.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>