<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 로그아웃 처리
session.invalidate();

// 메인페이지로 포워딩
response.sendRedirect("sessionTest3_main.jsp");
%>
