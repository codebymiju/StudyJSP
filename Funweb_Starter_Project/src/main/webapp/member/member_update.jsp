<%@page import="member.MemberDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//member_info.jsp 페이지로부터 전달받은 폼 파라미터를 사용하여 
//funweb.member 테이블의 레코드 수정(UPDATE)
	request.setCharacterEncoding("UTF-8");
	
	MemberDTO member = new MemberDTO();
	
	member.setId(request.getParameter("id"));
	member.setPass(request.getParameter("oldPass")); // 기존 패스워드(확인용)
	member.setName(request.getParameter("name"));
	member.setEmail(request.getParameter("email"));
	member.setPost_code(request.getParameter("post_code"));
	member.setAddress1(request.getParameter("address1"));
	member.setAddress2(request.getParameter("address2"));
	member.setPhone(request.getParameter("phone"));
	member.setMobile(request.getParameter("mobile"));

// out.println(member.toString()); // 출력문 안에서만 toString() 생략됨 자동 실행
MemberDAO dao = new MemberDAO();

// MemberDAO 객체의 isRightUser() 메서드를 호출하여 패스워드 일치 여부 판별
// (로그인 작업과 동일하나 별도로 정의)
// => 파라미터 : MemberDTO 객체(member)   리턴타입 : boolean(isRightUser)
boolean isRightUser = dao.isRightUser(member);

//만약, 패스워드 일치 여부 판별 결과가 true 일 경우 수정 작업 수행
//단, 수정에 사용될 패스워드가 존재할 경우 패스워드도 수정하고, 아니면 패스워드 제외 후 수정
if(isRightUser) {
	boolean isChangePass = false; // 패스워드 변경 여부를 선택할 변수(true : 변경, false : 미변경)
	
	// 새 패스워드(newPass) 가 존재할 경우 MemberDTO 객체의 패스워드를 새 패스워드로 교체
	String newPass = request.getParameter("newPass");
	if(newPass != null && !newPass.equals("")) {
		member.setPass(newPass); // 신규 패스워드(변경용)
		// isChangePass 변수값을 true 로 변경
		isChangePass = true;
	} 
	
	// MemberDAO 객체의 updateMember() 메서드를 호출하여 회원 수정 작업 수행
	// => 파라미터 : MemberDTO 객체(member), 패스워드 변경 여부(isChangePass)
	//    리턴타입 : int(updateCount)
	int updateCount = dao.updateMember(member, isChangePass);
	
	// 회원 수정 결과 판별
	// 실패 시 자바스크립트를 사용하여 "회원 수정 실패!" 출력 후 이전페이지로 돌아가기
	// 아니면, 메인페이지(main.jsp)로 이동
	if(updateCount > 0) { // 성공
	%>
	<script>
		alert("회원 수정 성공!");
		location.href="member_info.jsp?id=<%=member.getId()%>";
	</script>
	<%
} else { // 실패
	%>
	<script>
		alert("회원 수정 실패!");
		history.back();
	</script>
	<%
}
}

%>