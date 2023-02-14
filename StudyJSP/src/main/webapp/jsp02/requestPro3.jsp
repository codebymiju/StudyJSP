<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>requestPro3</h1>
	<%
	request.setCharacterEncoding("UTF-8");
	
	// 입력 데이터를 가지고 와서 변수에 저장
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	String gender = request.getParameter("gender");
	String grade = request.getParameter("grade");
	String[] hobbies = request.getParameterValues("hobby");
	%>
	<form action="requestPro3.jsp" method="post">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><%=name %></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><%=age %></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><%=gender %></td>
			</tr>
			<tr>
				<td>학년</td>
				<td><%=grade %></td>
			</tr>
			<tr>
				<td>취미</td>
				<td>
					<%
					if(hobbies == null) {
						out.println("없음");
					} else {
						for(int i = 0; i < hobbies.length; i++) {
							out.println(hobbies[i] + " ");
						}						
					}
					%>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>