<%@page import="jsp09_jdbc_dao.Jsp8_2DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jsp09_jdbc_dao.Jsp8_2DAO"%>
<%@page import="jsp09_jdbc_dao.StudentDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select2</title>
			<script type="text/javascript">
				function confirmDelete(id){
				 	let result = confirm(id + " 회원을 삭제 하시겠습니까?");
				 	
				 	if(result) {
				 		location.href = "delete2.jsp?id=" + id ;
				 	} else {}
				}
			</script>
</head>
<body>
	<%
	// 만약, 세션 아이디가 "admin"이 아닐 경우 
	String id = (String)session.getAttribute("sId");
	
	// 만약, 전달받은 아이디가 세션 아이디("sId")와 다를 경우
	// 자바스크립트를 사용하여 "잘못된 접근입니다!" 출력 후 메인페이지로 이동
	if(id == null || !id.equals("admin")) { 
		// 주의! 동등비교 연산 수행 시 문자열 내용이 아닌 주소값 비교
		%>
		<script type="text/javascript">
			alert("잘못된 접근입니다!")
			location.href = "index.jsp";
		</script>
		<% 
	} 
	%>
	<h1>회원 목록</h1>
	<table border="1">
			<tr>
				<th width="100">이름</th>
				<th width="100">아이디</th>
				<th width="200">E-mail</th>
				<th width="50">성별</th>
				<th width="100">가입일</th>
				<th width="150">버튼버튼</th>
			</tr>		
	
		<%
		// 회원 목록 조회를 위해 Jsp8_2DAO 객체 생성 후 select() 메서드 호출
		
		Jsp8_2DAO dao = new Jsp8_2DAO();
		ArrayList clientList = dao.select();
		
		// 테이블 내에 회원 목록 출력 -> 반복
		for(int i = 0; i < clientList.size(); i++) {
		  //ArrayList 객체에 저장된 jsp8_2DTO 객체를 
			Jsp8_2DTO j = (Jsp8_2DTO)clientList.get(i);
	
		%>
			
			<tr align="center">
				<td><%=j.getName()%></td>
				<td><%=j.getId()%></td>
				<td><%=j.getEmail()%></td>
				<td><%=j.getGender()%></td>
				<td><%=j.getHire_date()%></td>
				<td>
					<input type="button" value="상세정보" 
					onclick ="location.href='select2_detail.jsp?id=<%=j.getId()%>'">
					<input type="button" value="삭제" onclick ="confirmDelete('<%=j.getId()%>')">
				</td>
			</tr>
			<%
			}
		%>
	</table>
</body>
</html>