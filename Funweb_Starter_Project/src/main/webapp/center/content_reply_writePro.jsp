<%@page import="board.FileBoardDTO"%>
<%@page import="board.BoardReplyDTO"%>
<%@page import="board.BoardReplyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
//INSERT 구문 샘플
//INSERT INTO board_reply VALUES(null, '아이디', '내용', now(), 글번호, '게시판타입');
//INSERT INTO board_reply VALUES(null, 'hong', '댓글입니다', now(), 36, 'driver');
//=> BoardReplyDAO - insertReplyBoard() 메서드로 INSERT 작업 수행
//=> 작업 성공 시 driver_content.jsp 페이지로 이동
// 실패 시 자바스크립트 "댓글 작성 실패!" 출력 후 이전페이지로 돌아가기

String id = (String)session.getAttribute("sId");
if (id == null) {
	%>
	<script>
		alert("잘못된 접근입니다!");
		history.back();
	</script>
	<% 
}

BoardReplyDTO board = new BoardReplyDTO();
board.setId(id);
board.setContent(request.getParameter("content"));
board.setRef_idx(Integer.parseInt(request.getParameter("ref_idx")));
board.setBoard_type(request.getParameter("board_type"));
// System.out.println(board);

BoardReplyDAO dao = new BoardReplyDAO();
int insertCount = dao.insertReplyBoard(board);

// 단, 공지사항과 드라이버 게시판이 다르므로 파일명 중 게시판 타입을 결합
if(insertCount > 0) {
	String board_type = board.getBoard_type();
	response.sendRedirect( board_type + "_content.jsp?idx=" + board.getRef_idx() + "&pageNum=" + request.getParameter("pageNum"));
 } else { %> 
	<script>
		alert("댓글 작성 실패!"); 
		history.back(); 
	</script>
<% } %>




