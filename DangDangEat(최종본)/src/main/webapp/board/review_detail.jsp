<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<style type="text/css">
@font-face {
	font-family: 'GmarketSansMedium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	font-family: "GmarketSansMedium";
}

#articleForm {
	width: 500px;
	height: 550px;
	border: 1px solid #D3D3D3;
	margin: auto;
}

h2 {
	font-family: "GmarketSansMedium";
	text-align: center;
}

table {
	/* 		border: 1px solid black; */
	border-collapse: collapse;
	width: 500px;
}

th {
	text-align: center;
}

td {
	width: 150px;
	text-align: center;
}

#basicInfoArea {
	height: 70px;
	text-align: center;
}

#articleContentArea {
 	margin-bottom: 30px; 
 	background: #D3D3D3; 
	margin-top: 20px; 
 	height: 350px; 
 	text-align: center; 
	overflow: auto; 
 	white-space: pre-line; 
}

#commandList {
	margin: auto;
	margin-bottom: 30px;
	width: 500px;
	text-align: center;
}

#insertForm {
	margin: auto;
	width: 500px;
	text-align: center;
}

#commentViewAria {
	margin: auto;
	margin-bottom: 30px; text-align : center;
	border: 1px solid #dddddd;
	text-align: center;
}

input {
	border-radius: 0px;
	font-family: "GmarketSansMedium";
}
</style>
</head>

<body>

	<jsp:include page="/inc/top.jsp"></jsp:include>
	<div class="container">
	<input type="hidden" name="pageNum" value="${param.pageNum }" >
	<input type="hidden" name="review_readcount" value="${review.review_readcount }" >
	<input type="hidden" name="review_code" value="${review.review_code }" >
		<!-- 게시판 상세내용 보기 -->	
		<section>
			<section>
				<table class="table">				
					<tr>
						<th width="70">제 목</th>
						<td colspan="3">${review.review_subject }</td>
					</tr>
					<tr>
						<th width="70">작성자</th>
						<td>${review.member_id }</td>
						<th width="70">작성일</th>
						<td><fmt:formatDate value="${review.review_date }"
								pattern="yy-MM-dd" /></td>					
					<tr>
						<th width="70">첨부파일</th>
						<td colspan="3"><a href="upload/${review.review_real_file }"
							download="${review.review_file }"> ${review.review_file } </a></td>
				</table>
			</section>
			
						<section id="articleContentArea">${review.review_content }
			</section>
		</section>
	</div>
	<section id="commandList">
	<c:if test="${not empty sessionScope.sId and sessionScope.sId eq 'admin'}">
		<input type="button" value="답변"
			onclick="location.href='ReviewReplyForm.bo?review_code=${param.review_code}&pageNum=${param.pageNum }'"></c:if>
		<c:if test="${sessionScope.sId eq 'admin' || sessionScope.sId == review.member_id}"><input type="button" value="수정"
			onclick="location.href='ReviewModifyForm.bo?review_code=${param.review_code}&pageNum=${param.pageNum }'">
		<input type="button" value="삭제"
			onclick="location.href='ReviewDeletePro.bo?review_code=${param.review_code}&pageNum=${param.pageNum }'"></c:if>
		<input type="button" value="목록"
			onclick="location.href='ReviewList.bo?pageNum=${param.pageNum}'">
	</section>

	<div class="clear"></div>

	<div class="clear"></div>
	<div class="container">
		<div id="commentViewArea">
			<!-- insertForm 영역(댓글 작성 영역) - 세션 아이디 존재 시에만 표시 -->
			<c:if
				test="${not empty sessionScope.sId and sessionScope.sId eq 'admin'}">
				<div id="insertForm">
					<form action="CommentWritePro.bo" method="post">
						<!-- 글번호, 게시판타입, 페이지번호를 함께 전달 -->
						<input type="hidden" name="review_code" value=${param.review_code }> 
						<input type="hidden" name="pageNum" value=${param.pageNum }>
						<input type="text" name="comment_content" id="replyTextarea"
							style="width: 300px">
						<!-- 							<textarea rows="3" cols="50" name="comment_content" id="replyTextarea"></textarea>  -->
						<c:if test="${not empty sessionScope.sId and sessionScope.sId eq 'admin'}"><input type="submit" value="등록" id="replySubmit"></c:if>
					</form>
				</div>
			</c:if>

			<!-- 댓글 표시 영역 -->
			<section
				style="margin-left: 400px; margin-right: 150px; margin-top: 30px; margin-bottom: 30px;">
				<%-- for(CommentBean comment : commentList) {} --%>
				<table style="width: 500px;">
					<c:forEach var="comment" items="${commentList }">

						<input type="hidden" name="comment_code" value=${param.comment_code }>
						<!-- 					<th width="70"></th> -->
						<tr>
							<td>${comment.member_id }</td>
							<td><fmt:formatDate value="${comment.comment_date }" pattern="yy-MM-dd" /></td>
							<td>${comment.comment_content }</td>	 								
							<c:if test="${not empty sessionScope.sId and sessionScope.sId eq 'admin'}"><td><input type="button" value="댓글삭제"
			onclick="location.href='CommentDelete.bo?review_code=${param.review_code}&pageNum=${param.pageNum }&comment_code=${comment.comment_code }'">
							</td></c:if>
						</tr>
					</c:forEach>
				</table>
			</section>
		</div>
	</div>
		  <!-- Footer-->
   <footer class="py-5 bg-dark">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>