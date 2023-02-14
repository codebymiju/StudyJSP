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
	
	// POST 방식에 대한 한글 처리
	request.setCharacterEncoding("UTF-8");

	// JDBC 작업 4단계.
	// 0단계. DB 연결에 필요한 정보 문자열 4가지를 변수에 별도로 저장
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/study_jsp5";
	String user = "root";
	String password = "1234";
	
	// 1단계. JDBC 드라이버 로드
	Class.forName(driver);
	
	// 2단계. DB 연결
	// => 연결 성공 시 java.sql.Connection 타입 객체 리턴됨
	Connection con = DriverManager.getConnection(url, user, password);
	
	String sql = "SELECT * FROM jsp8_2 WHERE id=?";
	PreparedStatement pstmt = con.prepareStatement(sql);
	pstmt.setString(1, id);
	
	ResultSet rs = pstmt.executeQuery(); // 조회라서 필요함
	
		if(rs.next()) {
				String name = rs.getString("name");
// 				String id = rs.getString("id"); 위에 이미 파라미터로 전달받음
				String passwd = rs.getString("passwd");
				String jumin = rs.getString("jumin");
				String email = rs.getString("email");
				String job =rs.getString("job");
				String gender = rs.getString("gender");
				String hobby = rs.getString("hobby");
				String content = rs.getString("content");
				Date hireDate = rs.getDate("hire_date");
			
			%>
			<table border="1" >
			<tr><td width="100" >이름</td><td width="200"><%=name %></td></tr>
			<tr>
				<td>ID</td><td><%=id %></td>
			</tr>
			<tr>
				<td>비밀번호</td><td><%=passwd %></td>
			</tr>
			<tr>
				<td>주민번호</td><td><%=jumin %></td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td><%=email %></td>
			</tr>
			<tr>
				<td>직업</td>
				<td><%=job %></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><%=gender %></td>
			</tr>
			<tr>
				<td>취미</td>
				<td><%=hobby %></td>
			</tr>
			<tr>
				<td>가입동기</td>
				<td><%=content %></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="삭제" onclick="confirmDelete('<%=id%>')">
					<input type="button" value="이전" onclick="history.back()">
				</td>
			</tr>
		<% 
		}
		%>
	</table>
</body>
</html>