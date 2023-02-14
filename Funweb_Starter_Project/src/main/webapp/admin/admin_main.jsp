<%@page import="java.util.List"%>
<%@page import="member.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 세션아이디가 null 이거나 "admin" 이 아닐 경우 "잘못된 접근입니다!" 
// 출력 후 메인페이지로 이동
String sId = (String)session.getAttribute("sId");
// 친절하게 alert 필요없이 그냥 꺼져라 처리 
// (주소창에서 admin 주소입력시 그냥 돌려보냄)
// script가 없기 때문에 head 위로 올려보내도 상관없음!
if(sId == null || !sId.equals("admin")) {
	response.sendRedirect("../main/main.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_main.jsp</title>
<script type="text/javascript">
function confirmDelete(id) {
	// confirm dialog 사용하여 "XXX 회원을 삭제하시겠습니까?" 확인 요청
	// => 결과값이 true 일 경우 location.href='member_delete.jsp' 페이지 이동
	let result = confirm(id + " 회원을 삭제하시겠습니까?");
	
	if(result) {
		location.href = "../member/member_delete.jsp?id=" + id;
	}
}
</script>
</head>
<body>
	<h1>admin_main.jsp - 관리자 페이지</h1>
	<!--1. member 테이블의 모든 레코드 조회하여 테이블에 출력 / jsp09_select2 참고 
		2. 상세정보는 두고 삭제버튼만 구현하기 / jsp09_select_detail2 참고-->
		<h2>회원 목록</h2>
		<table border="1"> <!-- 반복하는것 아니니 for문 밖에 -->
			<tr align="center">
				<th width="100">id</th>
				<th width="100">name</th>
				<th width="200">email</th>
				<th width="150">mobile</th>
				<th width="100">date</th>
				<th width="150">etc</th>				
			</tr>
			<%
				MemberDTO dto = new MemberDTO();
			
				String id = request.getParameter("id");
				
				MemberDAO dao = new MemberDAO();
				List<MemberDTO> memberList = dao.selectMemberList();
				
//				1. 범위 지정이 필요할 때 (몇번째를 꺼낸다 or 길이를 조정한다)				
// 				for(int i = 0; i < memberList.size(); i++) {
// 					MemberDTO member = memberList.get(i);
				
// 				2. 향상된 for문을 사용하여 List 객체에서 MemberDTO 구문에서 꺼내오기
				for(MemberDTO member : memberList) {
					%>
					<tr>
						<td><%=member.getId()%></td>
						<td><%=member.getName()%></td>
						<td><%=member.getEmail()%></td>
						<td><%=member.getMobile()%></td>
						<td><%=member.getDate()%></td>
						<td>
							<input type="button" value="상세정보" onclick="location.href='../member/member_info.jsp?id=<%=member.getId()%>'">
							<input type="button" value="삭제" onclick="confirmDelete('<%=member.getId()%>')">
						</td>
					</tr>
			<% } %>				
		</table>
</body>
</html>