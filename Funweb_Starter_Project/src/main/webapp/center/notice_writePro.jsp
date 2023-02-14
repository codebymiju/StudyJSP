<%@page import="board.BoardDAO"%>
<%@page import="board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// POST 방식 한글 처리
request.setCharacterEncoding("UTF-8");

// BoardDTO 객체 생성 후 파라미터 데이터 저장
BoardDTO board = new BoardDTO();
board.setName(request.getParameter("name"));
board.setPass(request.getParameter("pass"));
board.setSubject(request.getParameter("subject"));
board.setContent(request.getParameter("content"));
// System.out.println(board);

// BoardDAO 객체의 insertBoard() 메서드 호출하여 글쓰기 작업 수행(INSERT)
// => 파라미터 : BoardDTO 객체    리턴타입 : int(insertCount)
BoardDAO dao = new BoardDAO();
int insertCount = dao.insertBoard(board);

// 글쓰기 결과 판별
// 실패 시 자바스크립트를 사용하여 "글쓰기 실패!" 출력 후 이전페이지로 돌아가기
// 아니면, 게시물 목록(notice.jsp)로 이동
if(insertCount > 0) {
	response.sendRedirect("notice.jsp");
} else {
	%>
	<script>
		alert("글쓰기 실패!");
		history.back();
	</script>
	<%
}

%>