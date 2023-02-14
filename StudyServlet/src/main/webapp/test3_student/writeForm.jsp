<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>학생 추가</h1>
	<!-- 학생 정보(번호, 이름)를 입력받을 폼 생성 -->
	<!-- 
	11/25
	요청 주소로 jsp 페이지가 아닌 서블릿 주소를 지정
	 -->
	<form action="WritePro" method="post">
	
	<table border="1">
		<tr>
			<th>번호</th>
			<td><input type="text" name ="idx"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name ="name"></td>
		</tr>
		<tr align="right">
			<td colspan="2"><input type="submit" value="추가"> </td>
		</tr>
	</table>
	
	</form>
	
</body>
</html>