<%@page import="jsp09_jdbc_dao.StudentDAO"%>
<%@page import="jsp09_jdbc_dao.StudentDTO"%>
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
	
	// insertForm.jsp 페이지로부터 전달받은 파라미터(idx, name) 가져와서 변수에 저장
	int idx = Integer.parseInt(request.getParameter("idx"));
	String name = request.getParameter("name");
	
	//----------------------------------------------------------------------------
	// 데이터베이스에 사용될 데이터(파라미터)를 StudentDTO 객체에 저장
	// 1. StudentDTO 클래스 인스턴스 생성
	StudentDTO student = new StudentDTO();
	// 2. StudentDTO 인스턴스에 데이터 저장 => Setter 메서드 호출
	student.setIdx(idx);
	student.setName(name);
	//----------------------------------------------------------------------------
	// 데이터베이스 작업에 사용될 StudentDAO 인스턴스 생성
	StudentDAO dao = new StudentDAO();
	
	// StudentDAO 인스턴스의 insert() 메서드를 호출하여 회원 추가 작업 수행
	// => 파라미터 : StudentDTO 객체(student), 리턴타입 : int(insertCount)
	int insertCount = dao.insert(student); // 원래 pstmt.executeUpdate();썼는데 그대신 dao사용
	
	// 데이터 추가 작업 실패 시 
	// -> 자바스크립트를 사용하여 "학생 추가 실패!" 출력 후 이전페이지로 돌아가기
	// 추가 작업 성공시 > select.jsp 페이지로 이동
	
	if(insertCount> 0){
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