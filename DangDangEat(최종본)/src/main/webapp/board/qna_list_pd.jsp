<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="en">
    <head>
<!--   <link href="css/bootstrap.css" rel="stylesheet" type="text/css"> -->
  <style type="text/css">
	@font-face {
	    font-family: 'GmarketSansMedium';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	}
	
	body {
	    font-family:"GmarketSansMedium" ;
	}
	  
	  
	#listForm {
		width: 1224px;
        max-height: 610px;
        margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: 20px;
		width: 1024px;
	}
	
	#tr_top {
		background: #D3D3D3;
		text-align: center;
	}
	
	table td {
		text-align: center;
	}
	
	#subject {
		text-align: left;
		padding-left: 20px;
	}
	
	#pageList {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		margin: auto;
		width: 1024px;
		text-align: right;
		margin-top: 10px;
	}
	
	a {
		text-decoration: none;
	}
</style>
</head>
<body>

<%-- 		<jsp:include page="../inc/top.jsp"></jsp:include> --%>
	
	<!-- qna 리스트 -->
	<section id="listForm">
<!-- 	<h2>문의</h2> -->
    <table class="container">	
		<tr id="tr_top">
			<td width="100px">No</td>
			<td>Subject</td>
			<td width="150px">Writer</td>
			<td width="150px">Date</td>
		</tr>
		<%-- for(QnaBean qna : qnaList) {} --%>
		<c:forEach var="qna" items="${qnaList}">
			<tr>
				<td>${qna.qna_code }</td>
				<c:choose>
					<c:when test="${empty param.pageNum }">
						<c:set var="pageNum" value="1" />
					</c:when>
					<c:otherwise>
						<c:set var="pageNum" value="${param.pageNum }" />
					</c:otherwise>
				</c:choose>
				<td id="subject">
					<%-- ======================== 답글 관련 처리 ======================= --%>					
					<c:if test="${qna.qna_re_lev > 0 }">					
						<c:forEach var="i" begin="1" end="${qna.qna_re_lev }">
							&nbsp;&nbsp;
						</c:forEach>						
							<img src="img/reply.png">
					</c:if>
					<%-- =============================================================== --%>
					<c:if test="${qna.qna_secret eq 'N' }">
							<img alt="" src="img/lock.svg">
							<c:choose>
								<c:when test="${qna.member_id eq sId || sId eq 'admin'}">
									<a
										href="QnaDetail.bo?qna_code=${qna.qna_code }&pageNum=${pageNum }">
										<c:out value="${qna.qna_subject }"></c:out>
									</a>
								</c:when>
								<c:otherwise>비밀글은 작성자와 관리자만 볼 수 있습니다.</c:otherwise>
							</c:choose>
						</c:if> <c:if test="${qna.qna_secret eq 'Y' }">
							<a
								href="QnaDetail.bo?qna_code=${qna.qna_code }&pageNum=${pageNum }">
								${qna.qna_subject } </a>
						</c:if>
					</td>
					<td><c:if test="${qna.qna_secret eq 'N' }">
							<c:choose>
								<c:when test="${qna.member_id eq sId || sId eq 'admin'}">
									<c:out value="${qna.member_id }"></c:out>
								</c:when>
								<c:otherwise>비밀이개~</c:otherwise>
							</c:choose>
						</c:if> <c:if test="${qna.qna_secret eq 'Y' }">
						${qna.member_id }
					</c:if></td>
					<td>						
						<fmt:formatDate	value="${qna.qna_date }" pattern="yy-MM-dd" />
					</td>
				</tr>
			</c:forEach>
		</table>
	<section id="buttonArea">
		<form action="QnaList.bo">
			<input type="text" name="keyword">
			<input type="submit" value="검색">
			&nbsp;&nbsp;<c:if test="${not empty sessionScope.sId}">	
			<input type="button" value="글쓰기" onclick="location.href='QnaWriteForm.bo'" /></c:if>
		</form>
	</section>
	<section id="pageList">
		
		<c:choose>
			<c:when test="${pageNum > 1}">
				<input type="button" value="이전" onclick="location.href='QnaList.bo?pageNum=${pageNum - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
			
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
			<c:choose>
				<c:when test="${pageNum eq i}">
					${i }
				</c:when>
				<c:otherwise>
					<a href="QnaList.bo?pageNum=${i }">${i }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${pageNum < pageInfo.maxPage}">
				<input type="button" value="다음" onclick="location.href='QnaList.bo?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>
	</section>