<%@page import="java.util.List"%>
<%@page import="test3_student.StudentDTO"%>
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
	// 11/25
	 List<StudentDTO> studentList = (List<StudentDTO>)request.getAttribute("studentList");
	
	// 배열과 마찬가지로 ArrayList 객체로 for문을 통해
	// 배열의 length 속성 대신 ArrayList 객체의 size() 메서드 이용하여 크기 확인
	for(int i = 0; i < studentList.size(); i++) {
		
		// 위의 코드를 한줄로 결합 (공통되는 o를 묶음)
		StudentDTO student = (StudentDTO)studentList.get(i);
		%>
			<tr>
				<td><%=student.getIdx() %></td>
				<td><%=student.getName()%></td>
			</tr>		
	<%}%>
	</table>
</body>
</html>