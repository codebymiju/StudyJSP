<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
		function confirm_logout() {
			let result = confirm("로그아웃 하시겠습니까?");
			if(result) {
				location.href="../member/logout.jsp";
			}
		}
	</script>
<header>
  <!-- login join -->
<%
//세션에 저장된 세션 아이디("sId") 가져와서 변수 sId 에 저장
String sId = (String)session.getAttribute("sId"); // 로그인 안된상태면 null값 리턴
%>
  <div id="login">
  <!-- 세션 아이디가 존재하지 않으면 login, join 링크 표시 -->
  <!-- 세션 아이디가 존재하면 아이디, logout 링크 표시 -->
  <%
  if(sId == null || sId.equals("")) { %>
	    <a href="../member/login.jsp">login</a> |
	  	<a href="../member/join.jsp">join</a>
  <% } else { %>
  		<a href="../member/member_info.jsp?id=<%=sId%>"><%=sId%>님</a> | 
  	  	<a href="javascript:confirm_logout()">logout</a>
  	  	<% if(sId.equals("admin")){ %> 
			| <a href="../admin/admin_main.jsp">관리자페이지</a>
   	  	<% }%>
  <% } %>
  </div>
  <div class="clear"></div>
  <!-- 로고들어가는 곳 -->
  <div id="logo"><img src="../images/logo.gif"></div>
  <!-- 메뉴들어가는 곳 -->
  <nav id="top_menu">
  	<ul>
  		<li><a href="../main/main.jsp">HOME</a></li>
  		<li><a href="../company/welcome.jsp">COMPANY</a></li>
  		<li><a href="../company/welcome.jsp">SOLUTIONS</a></li>
  		<li><a href="../center/notice.jsp">CUSTOMER CENTER</a></li>
  		<% if(sId == null || sId.equals("")) {%>
  		<li><a href="#">CONTACT US</a></li>
  		<% } else { 
  			MemberDAO dao = new MemberDAO();
  			MemberDTO member = dao.selectMember(sId);
  			String email = member.getEmail(); 
  		%>
  		<li><a href="../mail/mail_form.jsp?sId=<%=sId%>">CONTACT US</a></li>
  		<% } %>
  	</ul>
  </nav>
</header>