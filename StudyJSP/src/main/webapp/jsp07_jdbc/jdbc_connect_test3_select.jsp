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
	// JDBC 4단계
	// 0단계.문자열 지정 (url 실패)
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user ="root";
	String password ="1234";
	
	// 1단계. 드라이버 클래스 로드
	Class.forName(driver);
	
	// 2단계. DB연결 
	Connection con = DriverManager.getConnection(url, user, password);
	
	// 3단계. SQL 구문 작성 및 전달 
	String sql = "SELECT * FROM student";
	PreparedStatement pstmt = con.prepareStatement(sql);
	
	// 4단계. SQL 구문 실행 및 결과 처리
	// PreparedStatement 객체의 executeQuery() 메서드를 호출하여 SELECT 구문 실행하고
	// 리턴되는 결과값을 java.sql.ResultSet 타입 변수에 저장
	ResultSet rs = pstmt.executeQuery();
	
	//   ResultSet 객체의 next() 메서드를 호출하여 커서를 다음 레코드로 이동시키기
	// => 레코드가 6개이므로 rs.next() 메서드를 6번 호출할 동안 true 리턴, 7번째에 false 리턴
	
	// ResultSet 객체의 getXXX() 메서드를 호출하여 접근한 레코드의 특정 컬럼 데이터 리턴
	// => 이 때, 파라미터로 접근할 컬럼의 인덱스(1부터 시작) 또는 컬럼명을 전달
	// => 주의! 컬럼명을 직접 지정할 경우 오타에 주의!
// 	out.println(rs.next() + "<br>");
// 	out.println(rs.getInt(1) + "<br>"); // 컬럼인덱스 1번에 대한 정수 데이터 전달받아 출력
// 	out.println(rs.getInt("idx") + "<br>"); // 컬럼명 "idx"에 대한 정수 데이터 전달받아 출력
// 	out.println(rs.next() + "<br>");
// 	out.println(rs.getInt(1) + "<br>");
// 	out.println(rs.next() + "<br>");
// 	out.println(rs.getInt(1) + "<br>");
// 	out.println(rs.next() + "<br>");
// 	out.println(rs.getInt(1) + "<br>");
// 	out.println(rs.next() + "<br>");
// 	out.println(rs.getInt(1) + "<br>");
// 	out.println(rs.next() + "<br>");
// 	out.println(rs.getInt(1) + "<br>");
// 	out.println(rs.next() + "<br>");
// 	out.println(rs.getInt(1));
	
    // 만약, 조회 결과가 단일 레코드(1개 레코드)일 경우 
	// if 문을 사용하여 rs.next() 결과가 true 일 때 레코드에 접근하여 데이터를 가져올 수 있다!
// 	if(rs.next()){
// 		out.println("다음 레코드 존재함!");
// 	} else {
// 		out.println("다음 레코드 존재하지 않음!");
// 	}

    // 조회할 레코드가 복수개(2개 이상)일 경우
	// if 문 대신 while 문을 사용하여 "다음 레코드가 존재할 동안" 반복
	while(rs.next()){
// 		out.println("다음 레코드 존재함!" + "<br>");
		int idx = rs.getInt(1);
// 		int idx = rs.getInt("idx");
		String name = rs.getString(2);
// 		String name = rs.getString("name");
		out.println(idx + ". " + name + "<br>");

	}
	%>
	
</body>
</html>