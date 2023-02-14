<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mail_form.jsp</title>
<%
MemberDAO dao = new MemberDAO();
String sId = (String)session.getAttribute("sId");
MemberDTO member = dao.selectMember(sId);
%>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더 들어가는곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문 메인 이미지 -->
		<div id="sub_img_center"></div>
		<!-- 왼쪽 메뉴 -->
		<jsp:include page="../inc/left.jsp"></jsp:include>
		<!-- 본문 내용 -->
			<article>
			<h1>Contact Us</h1>
			<form action="mail_pro.jsp?sId=<%=sId%>" method="post" >
				<table id="notice">
					<tr align="center">
						<td>제목</td>
						<td><input type="text" name="title" placeholder="ex) 문의사항 제목"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="content" rows="20" cols="40" placeholder="ex) 문의사항 내용 작성"></textarea></td>
					</tr>
				</table>
					<div id="table_search">
						<input type="submit" value="메일발송" class="btn" >
					</div>
			</form>
			<div class="clear"></div>
		</article>
		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>
