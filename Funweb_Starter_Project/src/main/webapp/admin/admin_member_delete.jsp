<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//세션아이디가 null 이거나 "admin" 이 아닐 경우 메인페이지로 이동
String sId = (String)session.getAttribute("sId");
if(sId == null || !sId.equals("admin")) {
	response.sendRedirect("../main/main.jsp");
}
%>