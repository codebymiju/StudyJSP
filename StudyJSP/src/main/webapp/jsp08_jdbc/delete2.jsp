<%@page import="javax.tools.DocumentationTool.Location"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <h1>이동 성공</h1>
<%
// URL 파라미터로 전달받은 아이디 가져와서 변수에 저장
	String id = request.getParameter("id");
// 	out.println(id);
	
	// jsp8_2 테이블에서 아이디가 일치하는 레코드 삭제
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user = "root";
	String password = "1234";
	
	Class.forName(driver);
	
	Connection con = DriverManager.getConnection(url, user, password);
	
	String sql = "DELETE FROM jsp8_2 WHERE id=?";
	PreparedStatement pstmt = con.prepareStatement(sql);
	pstmt.setString(1, id);
	
	int count = pstmt.executeUpdate();
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
	
			