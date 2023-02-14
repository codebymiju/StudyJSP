<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// 	CREATE TABLE jsp8_2(
// 			name VARCHAR(10) NOT NULL,
// 			id VARCHAR(10) UNIQUE NOT NULL, 
// 			passwd VARCHAR(16) NOT NULL,
// 			jumin VARCHAR(14) UNIQUE NOT NULL,
// 			email VARCHAR(50) UNIQUE NOT NULL,
// 			job VARCHAR(10) NOT NULL,
// 			gender CHAR(1) NOT NULL,
// 			hobby VARCHAR(10) NOT NULL,
// 			content VARCHAR(100) NOT NULL,
// 			hire_date DATE NOT NULL
// 	); 
//  -> mysql에 그대로 복붙 / desc jsp8_2로 확인하기

	request.setCharacterEncoding("UTF-8");

// insertForm2.jsp 페이지로부터 전달받은 폼 파라미터 가져와서 변수에 저장
	
	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	String jumin = request.getParameter("jumin1") + "-" + request.getParameter("jumin2");
	String email = request.getParameter("email1") + "@" + request.getParameter("email2");
	String job = request.getParameter("job");
	String gender = request.getParameter("gender");
	// 취미는 체크박스 형태로 복수개의 동일한 이름을 갖는 파라미터로 전달될 수 있으므로
	// request 객체의 getParameterValues() 메서드를 호출하여 복수개의 파라미터를 
	// String[] 타입 배열로 관리됨
	// => 각 배열에 저장된 각각의 취미를 "/" 구분자를 사용하여 hobby 변수에 결합
	String hobby = "";
	String[] arrHobbies = request.getParameterValues("hobby"); // 가져오는 데이터 타입이 스트링 배열
	
// 	for(int i = 0; i < arrHobbies.length; i ++) {
// 		hobby += arrHobbies[i] + "/";
// 	}
	
	// 향상된 for문 사용시
	for(String strHobby : arrHobbies) {
		hobby += strHobby + "/";
	}
	
	String content = request.getParameter("content");

// 	out.println(id + "<br>" + passwd + "<br>" + jumin + "<br>" + email + "<br>" + job + "<br>" + 
// 	gender + "<br>" + hobby + "<br>" + content);

// jsp8_2 테이블에 1개 레코드에 해당하는 모든 데이터 저장
// 단, 입사일(hire_date)는 SQL 구문의 now() 함수 사용하여 DB 서버의 현재 날짜, 시각정보를 사용
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user = "root";
	String password = "1234";
	
	Class.forName(driver);
	
	Connection con = DriverManager.getConnection(url, user, password);
	
	String sql = "INSERT INTO jsp8_2 VALUES(?,?,?,?,?,?,?,?,?,now())";
	PreparedStatement pstmt = con.prepareStatement(sql);
	
	pstmt.setString(1, name);
	pstmt.setString(2, id);
	pstmt.setString(3, passwd);
	pstmt.setString(4, jumin);
	pstmt.setString(5, email);
	pstmt.setString(6, job);
	pstmt.setString(7, gender);
	pstmt.setString(8, hobby);
	pstmt.setString(9, content);
	
	int count = pstmt.executeUpdate();
	
	if(count > 0) {
		response.sendRedirect("select2.jsp");
	} else {
		%>
		<script>
			alert("회원 가입 실패!");
			history.back();
		</script>
		
		<%
	}

%>