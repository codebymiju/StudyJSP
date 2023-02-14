<%@page import="board.BoardDAO"%>
<%@page import="board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

int idx = Integer.parseInt(request.getParameter("idx"));
String pageNum = request.getParameter("pageNum");

BoardDTO board = new BoardDTO();

board.setIdx(idx);
board.setName(request.getParameter("name"));
board.setPass(request.getParameter("pass"));
board.setSubject(request.getParameter("subject"));
board.setContent(request.getParameter("content"));

BoardDAO dao = new BoardDAO();
int updateCount = dao.updateBoard(board);

if(updateCount > 0) {
	response.sendRedirect("notice_content.jsp?idx=" + idx + "&pageNum=" + pageNum);
} else { %>
	<script>
		alert("글 수정 실패!");
		history.back();
	</script>
	
<%}%>