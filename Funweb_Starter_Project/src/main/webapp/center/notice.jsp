<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
// String sId = (String)session.getAttribute("sId");

// board 테이블의 게시물 목록 조회
BoardDAO dao = new BoardDAO();

// BoardDAO 객체의 selectBoardList() 메서드를 호출하여 게시물 목록 조회
// => 파라미터 : 없음, 리턴타입 : List<BoardDTO>(boardList)
// (단, 임시로 페이징 처리 없이 전체 목록 조회)
// List<BoardDTO> boardList = dao.selectBoardList();
// ------------------------------------------------------------------------------
// 페이징 처리에서 사용되는 게시물 목록 조회를 위한 계산 작업
// 1. 한 페이지에서 표시할 게시물 목록 수 설정
int listLimit = 10; // 한 페이지에서 표시할 게시물 목록을 10개로 제한

// 2. 현재 페이지 번호 설정(pageNum 파라미터 사용)
// => pageNum 파라미터가 존재하면 해당 값을 저장하고, 아니면 기본 값 1 사용
int pageNum = 1;
if(request.getParameter("pageNum") != null) {
	pageNum = Integer.parseInt(request.getParameter("pageNum"));
}
// System.out.println(pageNum);

/*
3. 현재 페이지에서 목록으로 표시할 첫 게시물의 행(레코드) 번호 계산
   => (현재 페이지번호 - 1) * 페이지 당 게시물 목록 갯수
------------------------------------------------------------------------------------
pageNum(페이지번호)  listLimit(게시물 수)    startRow(시작행번호)   endRow(끝행번호)
------------------------------------------------------------------------------------
        1                    10              (1 - 1) * 10 =  0행        (~ 9행)
        2                    10              (2 - 1) * 10 = 10행       (~ 19행)
        3                    10              (3 - 1) * 10 = 20행       (~ 29행)
*/
int startRow = (pageNum - 1) * listLimit;
// 끝 행번호는 계산 불필요(시작행번호부터 시작하여 페이지 당 게시물 수 만큼 조회)

// BoardDAO 객체의 selectBoardList() 메서드를 호출하여 게시물 목록 조회
// => 파라미터 : 시작행번호, 페이지 당 게시물 목록 수 
// => 리턴타입 : List<BoardDTO>(boardList)
List<BoardDTO> boardList = dao.selectBoardList(startRow, listLimit);

// 22/11/21
pageContext.setAttribute("boardList", boardList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>center/notice.jsp</title>
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
		<jsp:include page="../inc/left.jsp" />
		<!-- 본문 내용 -->
		<article>
			<h1>Notice</h1>
			<table id="notice">
				<tr>
					<th class="tno">No.</th>
					<th class="ttitle">Title</th>
					<th class="twrite">Write</th>
					<th class="tdate">Date</th>
					<th class="tread">Read</th>
				</tr>
				<!-- 글목록을 반복 표시할 위치 -->
				<!-- List 객체(boardList) 크기만큼 반복 -->
				
				<c:forEach var="board" items="${boardList}" >
					<!-- 제목 행을 클릭 시 글 상세 정보 표시(notice_content.jsp) 로 이동 -->
					<!-- 파라미터로 글번호(idx) 값 전달 -->
					<tr onclick="location.href='notice_content.jsp?idx=${board.idx}&pageNum=<c:out value='${param.pageNum}' default='1' />'">
						<td>${board.idx}</td>
						<td class="td_title">${board.subject}</td>
						<td>${board.name}</td>
<%-- 						<td><fmt:formatDate value="${board.date}"/></td> --%>
<%-- 							<td><fmt:formatDate value="${board.date}" type="date"/></td> --%>
<%-- 							<td><fmt:formatDate value="${board.date}" type="time"/></td> --%>
<%-- 							<td><fmt:formatDate value="${board.date}" type="both"/></td> --%>
							<td><fmt:formatDate value="${board.date}" pattern="yy-MM-dd hh:mm"/></td>
						<td>${board.readcount}</td>
					</tr>
				</c:forEach>
			</table>
			<div id="table_search">
			<c:if test="${not empty sessionScope.sId and sessionScope.sId eq 'admin'}">
				<input type="button" value="글쓰기" class="btn" onclick="location.href='notice_write.jsp'">
			</c:if>
			</div>
			<div id="table_search">
				<form action="notice_search.jsp" method="get">
					<input type="text" name="keyword" class="input_box">
					<input type="submit" value="Search" class="btn">
				</form>
			</div>

			<div class="clear"></div>
			<div id="page_control">
				<%
				// 한 페이지에서 표시할 페이지 목록(번호) 갯수 계산
				// 1. BoardDAO 객체의 selectListCount() 메서드를 호출하여 전체 게시물 수 조회(페이지 목록 계산에 사용)
				// => 파라미터 : 없음, 리턴타입 : int(listCount)
				int listCount = dao.selectListCount();
// 				System.out.println("총 게시물 수 : " + listCount);
				
				// 2. 한 페이지에서 표시할 페이지 목록 갯수 설정
				int pageListLimit = 10; // 한 페이지에서 표시할 페이지 목록을 3개로 제한
				
				// 3. 전체 페이지 목록 수 계산
				// => 전체 게시물 수를 페이지 당 게시물 목록 수로 나눈 몫 계산
				// ex) 총 게시물 수 11개 일 때 페이지 수 : 2(10개 출력 후 남은 1개 1페이지 필요)
				//     총 게시물 수 21개 일 때 페이지 수 : 3(10개 * 2 출력 후 남은 1개 1페이지 필요)
				//     총 게시물 수 30개 일 때 페이지 수 : 3(10개 * 3 출력 후 남은 게시물 없음)
// 				int maxPage = listCount / listLimit; // 10개씩 분할했을 때 페이지 수 계산
				
// 				// 만약, 10개씩 분할 후 나머지가 존재하면 1 페이지 추가
// 				if(listCount % listLimit > 0) {
// 					maxPage++;
// 				}

				// 삼항연산자(= 조건연산자)를 활용하여 동일한 계산 수행
				// (조건식 ? 값1 : 값2)
				// => 나눗셈 결과에 추가로 1 페이지를 더할지 말지 판별하여 다른 값 덧셈
				int maxPage = listCount / listLimit 
								+ (listCount % listLimit == 0 ? 0 : 1); 
// 				System.out.println("전체 페이지 수 : " + maxPage);

				
				// 4. 시작 페이지 번호 계산
				// => (현재페이지번호 - 1) / 페이지목록갯수 * 페이지목록갯수 + 1
				// ex) 현재페이지 : 1 => 시작페이지 = (1 - 1) / 10 * 10 + 1 = 1 페이지
				//     현재페이지 : 2 => 시작페이지 = (2 - 1) / 10 * 10 + 1 = 1 페이지
				//     현재페이지 : 10 => 시작페이지 = (10 - 1) / 10 * 10 + 1 = 1 페이지
				//     현재페이지 : 15 => 시작페이지 = (15 - 1) / 10 * 10 + 1 = 11 페이지
				int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
				
				// 5. 끝 페이지 번호 계산
				// => 시작 페이지 + 페이지목록갯수 - 1
				int endPage = startPage + pageListLimit - 1;
				
				// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
				//    클 경우, 끝 페이지 번호를 최대 페이지 번호로 교체
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
				// 22/11/21 추가 
				pageContext.setAttribute("pageNum", pageNum);
				pageContext.setAttribute("maxPage", maxPage);
				pageContext.setAttribute("startPage", startPage);
				pageContext.setAttribute("endPage", endPage);
				%>
				
				<!-- 이전 페이지(Prev) 버튼 클릭 시 현재 페이지번호 - 1 값 전달 -->
				<!-- 단, 현재 페이지번호가 1보다 클 경우 - 1 값을 전달, 아니면 링크 동작 제거 -->
				<c:choose> 
					<c:when test="${pageNum gt 1}"> <%--조건절(if)--%>
						<a href="notice.jsp?pageNum=${pageNum -1 }">Prev</a>
					</c:when>
					<c:otherwise> <%--else--%>
						<a href="javascript:void(0)">Prev</a>
					</c:otherwise>
				</c:choose>
				
				<!-- for 문을 사용하여 시작페이지 ~ 끝페이지 까지 페이지 번호 표시 -->
				<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					<c:choose>
						<c:when test="${pageNum eq i}">
							<a href="javascript:void(0)">${i}</a>
						</c:when>
						<c:otherwise>
							<a href="notice.jsp?pageNum=${i}">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				
				<!-- 다음 페이지(Next) 버튼 클릭 시 현재 페이지번호 + 1 값 전달 -->
				<!-- 단, 현재 페이지번호가 끝페이지 번호보다 작을 경우 + 1 값을 전달, 아니면 링크 동작 제거 -->
				<c:choose>
					<c:when test="${pageNum lt maxPage }">
						<a href="notice.jsp?pageNum=${pageNum + 1}">Next</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)">Next</a>
					</c:otherwise>
				</c:choose>
			</div>
		</article>

		<div class="clear"></div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
</body>
</html>