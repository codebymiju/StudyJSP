<%@page import="jsp09_jdbc_dao.Jsp8_2DTO"%>
<%@page import="jsp09_jdbc_dao.Jsp8_2DAO"%>
<%@page import="java.sql.Date"%>
<%@page import="javax.xml.crypto.Data"%>
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
<title>select2_detail.jsp</title>
<script type="text/javascript">
	function confirmDelete(id) {
		let result = confirm("삭제하시겠습니까?");
		
		if(result){
			location.href = "delete2.jsp?id=" + id;
		}
	}
</script>
</head>
<body>
<h1>회원 상세정보</h1>	
	<%
	String id = request.getParameter("id");
	String sId = (String)session.getAttribute("sId");
	
	// 만약, 전달받은 아이디가 세션 아이디("sId")와 다를 경우
	// 자바스크립트를 사용하여 "잘못된 접근입니다!" 출력 후 메인페이지로 이동
	if(id == null || sId == null ||!id.equals(sId) && !sId.equals("admin")) { 
		// 주의! 동등비교 연산 수행 시 문자열 내용이 아닌 주소값 비교
		%>
		<script type="text/javascript">
			alert("잘못된 접근입니다!")
			location.href = "index.jsp";
		</script>
		<% 
	} 
	
	// 회원 상세정보 조회를 수행하기 위해
	// Jsp8_2DAO 객체 생성 후 selectDetail() 메서드 호출
	// => 파라미터 : 아이디(id)    리턴타입 : Jsp8_2DTO(dto)
	Jsp8_2DAO dao = new Jsp8_2DAO();
	Jsp8_2DTO dto = dao.selectDetail(id);
	
		
		if(dto != null) {
			%>
			<table border="1" >
			<tr><td width="100" >이름</td><td width="200"><%=dto.getName()%></td></tr>
			<tr>
				<td>ID</td><td><%=dto.getId()%></td>
			</tr>
<!-- 			<tr> -->
<%-- 				<td>비밀번호</td><td><%=passwd %></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>주민번호</td><td><%=dto.getJumin()%></td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td><%=dto.getEmail()%></td>
			</tr>
			<tr>
				<td>직업</td>
				<td><%=dto.getJob()%></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><%=dto.getGender()%></td>
			</tr>
			<tr>
				<td>취미</td>
				<td><%=dto.getHobby()%></td>
			</tr>
			<tr>
				<td>가입동기</td>
				<td><%=dto.getContent()%></td>
			</tr>
			<tr>
				<td>가입일</td>
				<td><%=dto.getHire_date()%></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="삭제" onclick="confirmDelete('<%=dto.getId()%>')">
					<input type="button" value="이전" onclick="history.back()">
				</td>
			</tr>
		<% 
		} // if문 끝
		%>
	</table>
</body>
</html>