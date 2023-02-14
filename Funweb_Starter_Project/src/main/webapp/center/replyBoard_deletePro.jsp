<%@page import="board.BoardReplyDAO"%>
<%@page import="board.BoardReplyDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//파라미터 가져와서 확인
BoardReplyDTO replyBoard = new BoardReplyDTO();
int idx = Integer.parseInt(request.getParameter("idx")); // 댓글 번호
String board_type = request.getParameter("board_type"); // 컨텐츠 게시판 타입
String pageNum = request.getParameter("pageNum"); // 컨텐츠 페이지
int ref_idx = Integer.parseInt(request.getParameter("ref_idx")); // 컨텐츠 글번호

// replyBoard.setIdx(idx); // 매번 까먹는거 이거 안하면 null값 나온다

//BoardDAO 객체의 deleteBoard() 메서드를 호출하여 글 삭제 작업 수행
//=> 파라미터 : 글번호, 패스워드   리턴타입 : int(deleteCount)
//=> SQL : board 테이블에서 글번호와 패스워드가 일치하는 레코드 삭제(DELETE)
BoardReplyDAO dao = new BoardReplyDAO();
int deleteCount = dao.deleteReply(idx);
System.out.println(deleteCount);

//삭제 결과 판별
//성공 시 글 목록 페이지(notice.jsp)로 이동하고
//(=> 파라미터로 페이지번호 전달 필요)
//아니면(실패 시) 자바스크립트로 "글 삭제 실패!" 출력 후 이전페이지로 돌아가기

if(deleteCount > 0) {
	response.sendRedirect(board_type + "_content.jsp?pageNum=" + pageNum +"&idx=" + ref_idx);
} else { %>
	<script>
		alert("글 삭제 실패!");
		history.back();
	</script>
<% }%>