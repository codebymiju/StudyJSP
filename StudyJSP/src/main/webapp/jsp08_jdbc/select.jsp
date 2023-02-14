<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>학생 목록</h1>
	<%
	// 0단계. 문자열 지정
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user = "root";
	String password ="1234";
	
	// 1단계. 드라이버 연결
	Class.forName(driver);
	
	// 2단계. DB 연결
	Connection con = DriverManager.getConnection(url, user, password);
	
	// 3단계. SQL 구문 작성 및 연결
	String sql = "SELECT * FROM student";
	PreparedStatement pstmt = con.prepareStatement(sql);
	
	// 4단계. SQL 구문 실행 및 결과 처리
	
	ResultSet rs = pstmt.executeQuery();
	

	%>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>이름</th>
		</tr>
		<% 
		while(rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			%>
			<tr>
				<td><%=idx %></td>
				<td><%=name %></td>
			</tr>		
			<% 	
		}
		%>
	</table>
</body>
</html>