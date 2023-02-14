<%@page import="jsp09_jdbc_dao.Jsp8_2DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 한글 들어가는 경우에만 인코딩 실행
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("id");
String passwd = request.getParameter("passwd");

out.println("아이디 : " + id + " 패스워드 : " + passwd);

Jsp8_2DAO dao = new Jsp8_2DAO();
boolean isLoginSuccess = dao.login(id, passwd);

if (isLoginSuccess) { // 로그인 성공
	session.setAttribute("sId", id);
	response.sendRedirect("index.jsp");
} else {
%>
	<script >
	alert("로그인 실패!");
	history.back();
	</script>
<%	
}
%>
