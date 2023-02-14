<%@page import="free.FreeBoardDTO"%>
<%@page import="free.FreeBoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main/main.jsp</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/front.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<!-- inc/top.jsp 페이지 포함시키기 -->
		<jsp:include page="../inc/top.jsp"></jsp:include>
		<!-- 헤더 들어가는곳 -->
		  
		<div class="clear"></div>   
		<!-- 본문들어가는 곳 -->
		<!-- 메인 이미지 -->
		<div id="main_img"><img src="../images/main_img.jpg"></div>
		<!-- 본문 내용 -->
		<article id="front">
		  	<div id="solution">
		  		<div id="hosting">
		  			<h3>Web Hosting Solution</h3>
					<p>H<br>o<br>s<br>t<br>i<br>n<br>g<br></p>
		  		</div>
		  		<div id="security">
		  		  	<h3>Web Security Solution</h3>
					<p>S<br>e<br>c<br>u<br>r<br>i<br>t<br>y</p>
		  		</div>
		  		<div id="payment">
		  			<h3>Web Payment Solution</h3>
		  			<p>P<br>a<br>y<br>m<br>e<br>n<br>t<br></p>
		  		</div>
		  	</div>
		  	<div class="clear"></div>
			<div id="news_notice">
				<h3><span class="orange">Security</span> News</h3>
				<%
		  		// BoardDAO 객체의 selectRecentBoardList() 메서드를 호출하여
		  		// 최근 게시물 5개 목록 조회 후 표시
		  		// -> 파라미터 : 없음 		리턴 타입 : List<BoardDTO>(boardList)
		  		FreeBoardDAO dao2 = new FreeBoardDAO();
		  		List<FreeBoardDTO> boardList2 = dao2.selectRecentBoardList();
		  		SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd");
		  		%>
		  		<table>
		  			<%for(FreeBoardDTO board : boardList2) {%> <!-- for문 생각 못했음! 공부! -->
		  			<tr>
		  				<td class="td_title">
		  				<a href="../center/free_board_content.jsp?idx=<%=board.getIdx()%>"><%=board.getSubject()%></a>
		  				</td>
<%-- 		  				<td><%=board.getName()%></td>  --%>
		  				<td width="100"><%=sdf2.format(board.getDate())%></td>
		  			</tr>
		  			<%} %>
		  		</table>
			</div>
		
			<div id="news_notice">
		  		<h3 class="brown">News &amp; Notice</h3>
		  		<!-- 공지사항 게시물(board 테이블) 중 최근 게시물 5개 표시 영역 -->
		  		<%
		  		// BoardDAO 객체의 selectRecentBoardList() 메서드를 호출하여
		  		// 최근 게시물 5개 목록 조회 후 표시
		  		// -> 파라미터 : 없음 		리턴 타입 : List<BoardDTO>(boardList)
		  		BoardDAO dao = new BoardDAO();
		  		List<BoardDTO> boardList = dao.selectRecentBoardList();
		  		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		  		%>
		  		<table>
		  			<%for(BoardDTO board : boardList) {%> <!-- for문 생각 못했음! 공부! -->
		  			<tr>
		  				<td class="td_title">
		  				<a href="../center/notice_content.jsp?idx=<%=board.getIdx()%>"><%=board.getSubject()%></a>
		  				</td>
<%-- 		  				<td><%=board.getName()%></td>  --%>
		  				<td width="100"><%=sdf.format(board.getDate())%></td>
		  			</tr>
		  			<%} %>
		  		</table>
		  	</div>
	  	</article>
		  
		<div class="clear"></div>  
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp"></jsp:include>
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


