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
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user = "root";
	String password = "1234";
	
	Class.forName(driver);
	
	Connection con = DriverManager.getConnection(url, user, password);
	
	
	// insertForm.jsp 페이지로부터 전달받은 파라미터(idx, name) 가져와서 변수에 저장
	int idx = Integer.parseInt(request.getParameter("idx"));
	String name = request.getParameter("name");
	
	out.println(idx + ", " + name);
	
	// study_jsp5 데이터베이스 student 테이블에 데이터 추가(insert)
	// -> 리턴되는 추가 작업 결과 변수(count)에 저장
	
	String sql = "INSERT INTO student VALUES(?, ?)";
	PreparedStatement pstmt = con.prepareStatement(sql);
	
	pstmt.setInt(1, idx);
	pstmt.setNString(2, name);
	
	int count = pstmt.executeUpdate();
	// 데이터 추가 작업 실패 시 
	// -> 자바스크립트를 사용하여 "학생 추가 실패!" 출력 후 이전페이지로 돌아가기
	// 추가 작업 성공시 > select.jsp 페이지로 이동
	
	if( count> 0){
		response.sendRedirect("select.jsp");
	} else {
		%>
		<script type="text/javascript">
		alert("학생 추가 실패!");
		history.back();
		</script>
		<%
	}
	
	%>
</body>
</html>