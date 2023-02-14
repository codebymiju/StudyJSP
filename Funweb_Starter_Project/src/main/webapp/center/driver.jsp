<%@page import="board.FileBoardDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="board.FileBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
FileBoardDAO dao = new FileBoardDAO();

//1. 한 페이지에서 표시할 게시물 목록 수 설정
int listLimit = 10;
// 2. 현재 페이지 번호 설정(pageNum 파라미터 사용)
int pageNum = 1;
if(request.getParameter("pageNum") != null) {
	pageNum = Integer.parseInt(request.getParameter("pageNum"));
}
// 3. 현재 페이지에서 목록으로 표시할 첫 게시물의 행(레코드) 번호 계산
int startRow = (pageNum - 1) * listLimit;

//---------------------------------------------------------
//파라미터로 전달받은 검색어(keyword) 가져와서 변수에 저장
String keyword = request.getParameter("keyword");

//만약, 전달받은 검색어가 null 이면 널스트링으로 변경
if(keyword == null) {
	keyword = "";
}
//---------------------------------------------------------
// selectFileBoardList() 메서드를 호출하여 게시물 목록 조회
// => 파라미터 : 시작행번호, 페이지 당 게시물 목록 수
//    (검색 기능 통합으로 인해 항상 키워드도 함께 전달)
// => 리턴타입 : List<FileBoardDTO>(fileBoardList)
List<FileBoardDTO> fileBoardList = dao.selectFileBoardList(startRow, listLimit, keyword);

// 날짜 형식 변경
SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/driver.jsp</title>
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
			<h1>Driver Download</h1>
			<table id="notice">
				<tr>
					<th class="tno">No.</th>
					<th class="ttitle">Title</th>
					<th class="twrite">Write</th>
					<th class="tdate">Date</th>
					<th class="tread">Read</th>
				</tr>
				<%
				for(FileBoardDTO fileBoard : fileBoardList) {
				%>
				<tr onclick="location.href='driver_content.jsp?idx=<%=fileBoard.getIdx()%>&pageNum=<%=pageNum%>'">
					<td><%=fileBoard.getIdx()%></td>
					<td class="left"><%=fileBoard.getSubject()%></td>
					<td><%=fileBoard.getName()%></td>
					<td><%=sdf.format(fileBoard.getDate()) %></td>
					<td><%=fileBoard.getReadcount()%></td>
				</tr>
				<% } %>
			</table>
			<div id="table_search">
				<input type="button" value="글쓰기" class="btn" onclick="location.href='driver_write.jsp'">
			</div>
			<!-- 검색 기능(driver.jsp 페이지 하나로 일반 목록과 검색어 목록 통합) -->
			<div id="table_search">
				<form action="driver.jsp" method="get">
					<input type="text" name="keyword" class="input_box">
					<input type="submit" value="Search" class="btn">
				</form>
			</div>

			<div class="clear"></div>
			<div id="page_control">
				<%
				// 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
				// 1. BoardDAO 객체의 selectListCount() 메서드를 호출하여 전체 게시물 수 조회
				//		 (검색 기능 통합으로 인해 키워드도 함께 전달)
				// (페이지 목록 계산에 사용)
				int listCount = dao.selectFileBoardListCount(keyword);
				
				// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
				int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
				
				// 3. 전체 페이지 목록 수 계산
				int maxPage = listCount / listLimit 
								+ (listCount % listLimit == 0 ? 0 : 1); 

				// 4. 시작 페이지 번호 계산
				int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
				
				// 5. 끝 페이지 번호 계산
				int endPage = startPage + pageListLimit - 1;
				
				// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
				//    클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				%>
				<!-- 이전 페이지(Prev) 버튼 클릭 시 현재 페이지번호 - 1 값 전달 -->
				<!-- 단, 현재 페이지번호가 1보다 클 경우 - 1 값을 전달, 아니면 링크 동작 제거 -->
				<%if(pageNum > 1) { %>
					<a href="driver.jsp?pageNum=<%=pageNum - 1%>">Prev</a>
				<%} else { %>
					<a href="javascript:void(0)">Prev</a>
				<%} %>
				
				<!-- for 문을 사용하여 시작페이지 ~ 끝페이지 까지 페이지 번호 표시 -->
				<%for(int i = startPage; i <= endPage; i++) {%>
					<!-- 단, 현재 페이지와 페이지 번호가 같을 경우 하이퍼링크 제거하고 번호만 표시 -->
					<%if(pageNum == i) { %>
						<a href="javascript:void(0)"><%=i%></a>
					<%} else { %>
						<a href="driver.jsp?pageNum=<%=i%>"><%=i%></a>
					<%} %>
				<%} %>
				
				<!-- 다음 페이지(Next) 버튼 클릭 시 현재 페이지번호 + 1 값 전달 -->
				<!-- 단, 현재 페이지번호가 끝페이지 번호보다 작을 경우 + 1 값을 전달, 아니면 링크 동작 제거 -->
				<%if(pageNum < maxPage) { %>
					<a href="driver.jsp?pageNum=<%=pageNum + 1%>">Next</a>
				<%} else { %>
					<a href="javascript:void(0)">Next</a>
				<%} %>
			</div>
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>


