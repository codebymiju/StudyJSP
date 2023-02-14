<%@page import="jsp09_jdbc_dao.Jsp8_2DAO"%>
<%@page import="jsp09_jdbc_dao.Jsp8_2DTO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

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

// 	==========================================================================
//  회원 가입에 필요한 정보를 Jsp8_2DTO 객체에 저장
	Jsp8_2DTO dto = new Jsp8_2DTO();
		dto.setName(name);
		dto.setId(id);
		dto.setPasswd(passwd);
		dto.setJumin(jumin);
		dto.setEmail(email);
		dto.setJob(job);
		dto.setGender(gender);
		dto.setHobby(hobby);
		dto.setContent(content);
			
// 	==========================================================================
//  Jsp8_2DAO 객체의 insert() 메서드를 호출하여 회원 가입 작업 요청
//  -> 파라미터 : Jsp8_2DTO 객체   리턴타입 : int(insertCount)
  	Jsp8_2DAO dao = new Jsp8_2DAO();
	int insertCount = dao.insert(dto);
	
	if(insertCount > 0) {
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