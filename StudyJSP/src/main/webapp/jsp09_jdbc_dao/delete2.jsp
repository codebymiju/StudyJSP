<%@page import="jsp09_jdbc_dao.Jsp8_2DTO"%>
<%@page import="jsp09_jdbc_dao.Jsp8_2DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <h1>이동 성공</h1>
<%
// URL 파라미터로 전달받은 아이디 가져와서 변수에 저장
	String id = request.getParameter("id");

//회원 삭제를 위한 Jsp8_2DAO 객체의 delete() 메서드 호출
//=> 파라미터 : 아이디    리턴타입 : int(count)
	Jsp8_2DAO dao = new Jsp8_2DAO();
	int count = dao.delete(id);
// 삭제 성공 시 select2.jsp 페이지로 이동
// 삭제 실패시 자바스크립트 사용하여 "삭제실패!" 출력 후 이전페이지로 돌아가기 
	if(count > 0) {
		response.sendRedirect("select2.jsp");
	} else {
		%>
		<script>
		alert("삭제 실패!");
		history.back();
		</script>
		<% 
	}
	%>
	
			