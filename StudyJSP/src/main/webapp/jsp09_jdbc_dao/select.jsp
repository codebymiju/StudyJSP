<%@page import="jsp09_jdbc_dao.StudentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jsp09_jdbc_dao.StudentDAO"%>
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
	<table border="1">
		<tr>
			<th>번호</th>
			<th>이름</th>
		</tr>
	<%
	// 학생 목록 조회를 위해 StudentDAO 인스턴스 생성 후 select() 메서드 호출
	// -> 파라미터 : 없음 	 	리턴타입 : ArrayList
	StudentDAO dao = new StudentDAO();
	ArrayList studentList = dao.select();
	
	// 배열과 마찬가지로 ArrayList 객체로 for문을 통해
	// 배열의 length 속성 대신 ArrayList 객체의 size() 메서드 이용하여 크기 확인
	for(int i = 0; i < studentList.size(); i++) {
		
// 		Object o = studentList.get(i);
// 		StudentDTO student = (StudentDTO)o;

		// 위의 코드를 한줄로 결합 (공통되는 o를 묶음)
		StudentDTO student = (StudentDTO)studentList.get(i);
		%>
			<tr>
				<!--  -->
				<td><%=student.getIdx() %></td>
				<td><%=student.getName()%></td>
			</tr>		
		<% 
	}
	%>
			<!-- ======================================== -->
	</table>
</body>
</html>