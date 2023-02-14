<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/member_info.jsp</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
		function confirmLeave(id) {
			let result = confirm("힘들게 가입시켰는데\n정말 탈퇴하시겠습니까?");
			if(result) {
				location.href="member_delete.jsp?id=" + id;
			}
		}
	</script>
</head>
<body>
	<%
	String sId = (String)session.getAttribute("sId");
	String id = request.getParameter("id");
	// 관리자가 아니라면 자신의 아이디와 sId 가 일치할때만 조회 권한 부여
	if(sId == null || id == null || id.equals("") || !sId.equals(id) && !sId.equals("admin")) { %>
		<script>
			alert("잘못된 접근입니다!");
			location.href = "../main/main.jsp";
		</script>
	<% }
	// MemberDAO 객체의 selectMember() 메서드를 호출하여 회원 정보 조회	  
	// -> 파라미터 : 파라미터 아이디 		리턴타입 : MemberDTO(member)
	
	MemberDAO dao = new MemberDAO();
	MemberDTO member = dao.selectMember(id);
  	%>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<!-- 헤더 들어가는곳 -->
		  
		<!-- 본문들어가는 곳 -->
		<!-- 본문 메인 이미지 -->
		  <div id="sub_img_member"></div>
		  <!-- 왼쪽 메뉴 -->
		  <nav id="sub_menu">
		  	<ul>
		  		<li><a href="#">Join us</a></li>
		  		<li><a href="#">Privacy policy</a></li>
		  	</ul>
		  </nav>
		  <!-- 본문 내용 -->
		  <article>
		  	<h1>Member Info</h1>
		  	<form action="member_update.jsp" method="post" id="join" name="fr">
		  		<fieldset>
		  			<legend>Basic Info</legend>
		  			<label>User Id(변경 불가)</label>
		  			<input type="text" name="id" class="id" id="id" readonly="readonly" value="<%=member.getId()%>">
		  			<input type="button" value="dup. check" class="dup" id="btn"><br>
		  			
		  			<label>Old Password</label>
		  			<input type="password" name="oldPass" id="oldPass" required="required" placeholder="패스워드 입력"><br> 
		  			
		  			<label>New Password</label>
		  			<input type="password" name="newPass" id="newPass" placeholder="변경시에만 입력"><br> 			
		  			
		  			<label>Retype New Password</label>
		  			<input type="password" name="newPass2" placeholder="변경시에만 입력"><br>
		  			
		  			<label>Name</label>
		  			<input type="text" name="name" id="name" required="required" value="<%=member.getName()%>"><br>
		  			
		  			<label>E-Mail</label>
		  			<input type="email" name="email" id="email" required="required" value="<%=member.getEmail()%>"><br>
		  			
		  			<label>Mobile Phone Number</label>
		  			<input type="text" name="mobile" required="required" value="<%=member.getMobile()%>"><br>
		  		</fieldset>
		  		
		  		<fieldset>
		  			<legend>Optional</legend>
		  			<label>Post Code</label>
		  			<input type="text" name="post_code" id ="post_code" placeholder="우편번호" value="<%=member.getPost_code()%>">
		  			<input type="button" value="주소검색"><br>
		  			<label>Address</label>
		  			<input type="text" name="address1" id="address1" placeholder="주소" value="<%=member.getAddress1()%>">
		  			<input type="text" name="address2" id="address2" placeholder="상세주소" value="<%=member.getAddress2()%>"><br>
		  			<label>Phone Number</label>
		  			<input type="text" name="phone" ><br>
		  		</fieldset>
		  		<div class="clear"></div>
		  		<div id="buttons">
		  			<input type="submit" value="Update" class="submit">
		  			<input type="reset" value="Cancel" class="cancel">
		  			<input type="reset" value="Leave" class="submit" onclick="confirmLeave('<%=member.getId()%>')">
		  		</div>
		  	</form>
		  </article>
		  
		  
		<div class="clear"></div>  
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


