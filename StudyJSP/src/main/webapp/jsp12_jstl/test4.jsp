<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="jsp11_dbcp.StudentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="idx2" value="3"/>
<%
request.setAttribute("idx", 3);
request.setAttribute("name", "홍길동");

List<StudentDTO> studentList = new ArrayList<StudentDTO>();
studentList.add(new StudentDTO(1, "홍길동"));
studentList.add(new StudentDTO(2, "이순신"));
studentList.add(new StudentDTO(3, "홍길동"));

request.setAttribute("studentList", studentList);

String[] names = {"은우여친", "장염민지", "알감자", "답글천사"};
request.setAttribute("names", names);

pageContext.forward("test4_result.jsp");
%>