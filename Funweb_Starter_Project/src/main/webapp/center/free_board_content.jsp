<%@page import="java.text.SimpleDateFormat"%>
<%@page import="free.FreeBoardDTO"%>
<%@page import="free.FreeBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//글번호, 페이지번호 파라미터 가져오기
//=> 단, 페이지번호는 다음 페이지로 전달하는 용도로만 사용하므로 String 타입 사용도 가능
int idx = Integer.parseInt(request.getParameter("idx")); 
String pageNum = request.getParameter("pageNum");

FreeBoardDAO dao = new FreeBoardDAO();
FreeBoardDTO freeBoard = new FreeBoardDTO();

// FileBoardDAO 객체의 updateReadcount() 메서드를 호출하여 게시물 조회수 증가 작업 수행
// => 파라미터 : 글번호(idx)   리턴타입 : void
dao.updateReadCount(idx);

// FileBoardDAO 객체의 selectBoard() 메서드를 호출하여 게시물 1개 조회 작업 수행
// => 파라미터 : 글번호(idx)   리턴타입 : BoardDTO(board)
freeBoard = dao.selectFreeBoard(idx);

// textarea 쓸 일이 있으니 꺼낼때만 줄바꿈 처리!
freeBoard.setContent(freeBoard.getContent().replaceAll(System.getProperty("line.separator"), "<br>"));

//날짜 표시 형식을 "xxxx-xx-xx xx:xx:xx"(년-월-일 시:분:초) 형식으로 변경 (SimpleDateFormat 객체)
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/free_content.jsp</title>
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
			<h1>Free Board Content</h1>
			<table id="notice">
				<!-- BoardDTO 객체에 저장된 데이터 표시 -->	
				<tr>
					<td>글번호</td>
					<td><%=freeBoard.getIdx()%></td>
					<td>글쓴이</td>
					<td><%=freeBoard.getName()%></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><%=freeBoard.getDate()%></td>
					<td>조회수</td>
					<td><%=freeBoard.getReadcount()%></td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><%=freeBoard.getSubject()%></td>
				</tr>
				<tr>
					<td>파일</td>
					<td colspan="3">
						<!-- 
						실제 파일과 연결하여 파일 다운로드 하이퍼링크 생성
						=> 하이퍼링크 href 속성에 파일 위치 경로명과 실제 파일명을 기술하고
						   download 속성 지정 시 파일 다운로드 기능이 동작(HTML5 에서 추가)
						-->
						<a href="../upload/<%=freeBoard.getReal_file()%>" download="<%=freeBoard.getOriginal_file()%>"><%=freeBoard.getOriginal_file()%></a>
					</td>
				</tr>
				<tr>
					<td height="300">내용</td>
					<td colspan="3"><%=freeBoard.getContent()%></td>
				</tr>
			</table>

			<div id="table_search">
				<input type="button" value="글수정" class="btn" onclick="location.href='free_board_update.jsp?idx=<%=freeBoard.getIdx()%>&pageNum=<%=pageNum%>'"> <input
					type="button" value="글삭제" class="btn" onclick="location.href='free_board_delete.jsp?idx=<%=freeBoard.getIdx()%>&pageNum=<%=pageNum%>'"> <input
					type="button" value="글목록" class="btn" onclick="location.href='free_board.jsp?pageNum=<%=pageNum%>'">
			</div>

			<div class="clear"></div>
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


