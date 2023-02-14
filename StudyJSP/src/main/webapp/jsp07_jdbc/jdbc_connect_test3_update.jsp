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
	// JDBC 4단계
	// 0단계.문자열 지정 (url 실패)
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user ="root";
	String password ="1234";
	
	// 1단계. 드라이버 클래스 로드
	Class.forName(driver);
	
	// 2단계. DB연결 (실패 - connection 타입 중 sql선택할것!)
	Connection con = DriverManager.getConnection(url, user, password);
	%>
	<h3>DB 연결 성공!</h3>
	
	<%
	// 3단계. SQL 구문 작성 및 전달
	// UPDATE 구문을 사용하여 번호(idx)가 1인 레코드의 이름(name) 을 "김길동"으로 변경
	// => 검색할 번호와 변경할 이름을 외부로부터 입력받았다고 가정
	int idx = 1;
	String name = "김길동";
	String sql = "UPDATE student SET name = ? WHERE idx = ?";
	PreparedStatement pstmt = con.prepareStatement(sql);
	
	
	// 만능문자 파라미터 2개를 데이터 교체
	pstmt.setString(1, name);
	pstmt.setInt(2, idx);
	
	
	// 4단계. SQL 구문 실행 및 결과 처리
	int count = pstmt.executeUpdate();
	
	%>
	<h3>SQL 구문 실행 완료! - <%=count %> 개 레코드</h3>
	
</body>
</html>