<%@page import="member.MemberDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 
 // join.jsp 페이지로부터 전달받은 폼 파라미터를 사용하여 funweb.member 테이블에 레코드 추가
// 1. 폼 파라미터 가져와서 저장(MemberDTO 객체 dto) 후 확인
	request.setCharacterEncoding("UTF-8");
	
	MemberDTO member = new MemberDTO();
	
	member.setId(request.getParameter("id"));
	member.setPass(request.getParameter("pass"));
	member.setName(request.getParameter("name"));
	member.setEmail(request.getParameter("email"));
	member.setPost_code(request.getParameter("post_code"));
	member.setAddress1(request.getParameter("address1"));
	member.setAddress2(request.getParameter("address2"));
	member.setPhone(request.getParameter("phone"));
	member.setMobile(request.getParameter("mobile"));

// out.println(member.toString()); // 출력문 안에서만 toString() 생략됨 자동 실행

//MemberDAO 객체의 insertMember() 메서드를 호출하여 회원가입 작업 수행
//=> 파라미터 : MemberDTO 객체(member)   리턴타입 : int(insertCount)
MemberDAO dao = new MemberDAO();
int insertCount = dao.insertMember(member);

//회원 가입 결과 판별
//실패 시 자바스크립트를 사용하여 "회원 가입 실패!" 출력 후 이전페이지로 돌아가기
//아니면, 메인페이지(main.jsp)로 이동
if(insertCount > 0) { 
// 	response.sendRedirect("../main/main.jsp");
	 %> <!-- 10/28 추가 / alert랑 response는 같이 사용불가 자바끼리 스크립트끼리 따로 -->
		<script>
			alert("회원 가입을 축하합니다!\n3,000 포인트가 적립되었습니다!");
			location.href = "../main/main.jsp";
		</script>
	<%
 } else {
	 %> 
	<script>
		alert("회원 가입 실패!");
		history.back();
	</script>
<%
} 
%>