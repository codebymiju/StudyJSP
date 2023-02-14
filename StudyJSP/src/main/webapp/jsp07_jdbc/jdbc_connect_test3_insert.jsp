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
	// 3단계. SQL 구문 작성 및 전달 (실패 / INSERT와 CREATE등 키워드 주의)
// 	String sql = "INSERT INTO student VALUES (3,'김미주')";
// 	PreparedStatement pstmt = con.prepareStatement(sql);
	
	// 외부로부터 추가할 레코드의 데이터를 입력받아 변수에 저장했다고 가정
	int idx = 4;
	String name = "김태희";
	
	// SQL 구문(INSERT) 작성 시 변수값을 SQL 구문에 전달하는 방법 2가지
	// 1. SQL 구문 작성 시 문자열 결합을 통해 변수값을 구분에 포함시키는 방법(추천X)
	// => CREATE 구문 등을 사용할 경우에는 사용하기도 하나 데이터 추가 시 잘 사용하지 않음
	// => 다른 데이터타입을 제외하고 문자 데이터의 경우 작은따옴표로 둘러싸서 표현해야하므로
	//    변수 결합 시 작은따옴표는 그대로 유지해야함

	// 	String sql = "INSERT INTO student VALUES (" + idx + ",'" + name + "')";
    // 	PreparedStatement pstmt = con.prepareStatement(sql);

	// 2. SQL 구문 작성 시 데이터가 위치할 부분을 만능문자 파라미터인 ? 기호로 먼저 채우고
	//    나중에 별도로 만능문자(=? = wildcard) 부분에 데이터를 교체하는 작업을 사용하는 방법(추천!!!!)
	
	// 2-1) SQL 구문 내에 데이터 부분을 만능문자(?)로 표기
	String sql = "INSERT INTO student VALUES (?,?)";
	PreparedStatement pstmt = con.prepareStatement(sql);
	// 2-2) PreparedStatement 객체의 setXXX() 메서드를 호출하여 만능문자를 데이터로 교체
	pstmt.setInt(1, idx); // 물음표의 타입은 setXXX()이 결정함! 
	pstmt.setString(2, name);
	
	// 4단계. SQL 구문 실행 및 결과 처리
	int insertCount = pstmt.executeUpdate();
	%>
	<h3>SQL 구문 실행 완료! - <%=insertCount %> 개 레코드</h3>
	
</body>
</html>