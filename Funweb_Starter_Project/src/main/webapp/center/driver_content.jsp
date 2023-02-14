<%@page import="board.BoardReplyDAO"%>
<%@page import="board.BoardReplyDTO"%>
<%@page import="java.util.List"%>
<%@page import="board.FileBoardDTO"%>
<%@page import="free.FreeBoardDTO"%>
<%@page import="board.FileBoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//글번호, 페이지번호 파라미터 가져오기
//=> 단, 페이지번호는 다음 페이지로 전달하는 용도로만 사용하므로 String 타입 사용도 가능
int idx = Integer.parseInt(request.getParameter("idx")); 
String pageNum = request.getParameter("pageNum");
// 11.15 - 만약, pageNum 값이 null 일 경우 "1"값으로 변경 or 아래에 주소값 넘기기
if(pageNum == null) {
	pageNum = "1";
}

FileBoardDAO dao = new FileBoardDAO();
FileBoardDTO fileBoard = new FileBoardDTO();

// FileBoardDAO 객체의 updateReadcount() 메서드를 호출하여 게시물 조회수 증가 작업 수행
// => 파라미터 : 글번호(idx)   리턴타입 : void
dao.updateReadcount(idx);

// FileBoardDAO 객체의 selectBoard() 메서드를 호출하여 게시물 1개 조회 작업 수행
// => 파라미터 : 글번호(idx)   리턴타입 : BoardDTO(board)
fileBoard = dao.selectFileBoard(idx);

// textarea 쓸 일이 있으니 꺼낼때만 줄바꿈 처리!
fileBoard.setContent(fileBoard.getContent().replaceAll(System.getProperty("line.separator"), "<br>"));

//날짜 표시 형식을 "xxxx-xx-xx xx:xx:xx"(년-월-일 시:분:초) 형식으로 변경 (SimpleDateFormat 객체)
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

// 게시판 타입을 변수에 저장
String board_type = "driver";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/driver_content.jsp</title>
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
			<h1>Driver Content</h1>
			<table id="notice">
				<!-- BoardDTO 객체에 저장된 데이터 표시 -->	
				<tr>
					<td>글번호</td>
					<td><%=fileBoard.getIdx()%></td>
					<td>글쓴이</td>
					<td><%=fileBoard.getName()%></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><%=fileBoard.getDate()%></td>
					<td>조회수</td>
					<td><%=fileBoard.getReadcount()%></td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><%=fileBoard.getSubject()%></td>
				</tr>
				<tr>
					<td>파일</td>
					<td colspan="3">
						<!-- 
						실제 파일과 연결하여 파일 다운로드 하이퍼링크 생성
						=> 하이퍼링크 href 속성에 파일 위치 경로명과 실제 파일명을 기술하고
						   download 속성 지정 시 파일 다운로드 기능이 동작(HTML5 에서 추가)
						-->
						<a href="../upload/<%=fileBoard.getReal_file()%>" download="<%=fileBoard.getOriginal_file()%>"><%=fileBoard.getOriginal_file()%></a>
					</td>
				</tr>
				<tr>
					<td height="300">내용</td>
					<td colspan="3"><%=fileBoard.getContent()%></td>
				</tr>
			</table>

			<div id="table_search">
				<!-- 11/15 추가 -->
				<% 
				if(session.getAttribute("sId") != null) { 
				%>
				<input type="button" value="글수정" class="btn" onclick="location.href='driver_update.jsp?idx=<%=fileBoard.getIdx()%>&pageNum=<%=pageNum%>'"> <input
					type="button" value="글삭제" class="btn" onclick="location.href='driver_delete.jsp?idx=<%=fileBoard.getIdx()%>&pageNum=<%=pageNum%>'"> 
				<% } %>
					<input
					type="button" value="글목록" class="btn" onclick="location.href='driver.jsp?pageNum=<%=pageNum%>'">
			</div>

			<div class="clear"></div>
			<!-- 11/15 추가 테이블 -->
			<div id="replyArea">
				<!-- insertForm 영역 : 댓글 작성 영역 / sId 존재 시에만 표시-->
				<% 
				if(session.getAttribute("sId") != null) { 
				%>
				<div id="insertForm">
					<form action="content_reply_writePro.jsp" method="post">
						<!-- 글번호, 게시판타입을 함께 전달 -->
						<input type="hidden" name="ref_idx" value="<%=idx%>">
						<input type="hidden" name="board_type" value="<%=board_type%>">
						<input type="hidden" name="pageNum" value="<%=pageNum%>">
						
						<textarea rows="3" cols="50" name="content" id="replyTextarea"></textarea>
						<input type="submit" value="등록" id="replySubmit"> 
					</form>
				</div>
				<% } %>
				<!-- replyViewArea : 댓글 표시 영역 -->
				<div id="replyViewArea">
					<%
					// *****실제로 페이징 처리해야하는 곳!*****(driver.jsp페이징 처리와 동일)
					// 페이징 처리를 위해 조회 시 필요한 값 임의 설정
					int replyPageNum = 1;
					int listLimit = 5;
					int startRow = (replyPageNum - 1) * listLimit; // 계산 생략
					
					// BoardReplyDAO - getReplyList() 메서드 호출하여 댓글 목록 가져오기
					// -> 파라미터 : 게시물글번호, 게시판타입   
					//    리턴타입 : List<BoardReplyDTO> (replyList)
					BoardReplyDAO replyDao = new BoardReplyDAO();
					List<BoardReplyDTO> replyList = replyDao.selectReplyList(idx, board_type, startRow, listLimit);
					// List 객체 크기만큼 반복
					for(BoardReplyDTO replyBoard : replyList) { %>
						<!-- a태그 안에 링크 넣어서 댓글 삭제하기 기능 추가! -->
			
						<a href="replyBoard_deletePro.jsp?idx=<%=replyBoard.getIdx()%>&pageNum=<%=pageNum%>&board_type=<%=replyBoard.getBoard_type()%>&ref_idx=<%=replyBoard.getRef_idx()%>">
						<img src="../images/center/delete.png" height="10px"></a>
						<span id="replyContent"><%=replyBoard.getContent()%></span>
						<span id="replyId"><%=replyBoard.getId()%></span>
						<span id="replyDate"><%=sdf.format(replyBoard.getDate())%></span><br>
						
					<%}%>	
				</div>
			
				<div id="page">
					<%
					int listCount = replyDao.selectReplyBoardListCount(board_type, idx);// 한 페이지에서 표시할 목록 갯수 조회
					int pageListLimit = 5; // 한 페이지 출력할 댓글 수
					int replyMaxPage = listCount / pageListLimit + (listCount % pageListLimit == 0 ? 0 : 1); // 전체 페이지 수 
					
					// 시작 페이지 번호 계산
					int replyStartPage = (replyPageNum -1) / pageListLimit * pageListLimit +1;// 시작 
					int replyEndPage = replyStartPage + pageListLimit -1;
					
					if(replyEndPage > replyMaxPage) {
						replyEndPage = replyMaxPage;
					} %>
					
					<% for(int i = replyStartPage; i <= replyEndPage; i++) { %>
						<% if(replyPageNum == i)  {%>
							<a href="javascript:void(0)"><%=i%></a>
						<%} else {%>
							<a href="javascript:void(0)"><%=i%></a>
						<%} %>
					<% } %>
				</div>
			</div>
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>