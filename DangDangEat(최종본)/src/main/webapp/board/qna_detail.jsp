<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="en">
    <head>
  <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
  <link href="css/styles.css" rel="stylesheet" />  
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
	#articleForm {
		width: 1224px;
		height: 550px;
		border: 1px solid #D3D3D3; 
		margin: auto;
	}	
	h2 {
		font-family:"GmarketSansMedium" ;
		text-align: center;
	}	
	table {
 		border-collapse: collapse;  
	 	width: 1224px;
	 	border-bottom-width: 1px;
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
	input[type=button], input[type=submit] {
		border-radius: 0px;
	    font-family:"GmarketSansMedium" ;
	}	
</style>
</head>
<body>	
	<jsp:include page="../inc/top.jsp"></jsp:include>
	
	<div class="container">
	<!-- qna 상세내용 보기 -->
	<section>
		<section>
			<table class="table">
			<tr><th width="70">제 목</th><td colspan="3" >${qna.qna_subject }</td></tr>
			<tr>
				<th width="70">작성자</th><td>${qna.member_id }</td>
				<th width="70">작성일</th>
				<td><fmt:formatDate value="${qna.qna_date }" pattern="yy-MM-dd" /></td>
			</tr>
			<tr>
				<th width="70">첨부파일</th>
				<td colspan="3">
					<a href="upload/${qna.qna_real_file }" download="${qna.qna_file }">
						${qna.qna_file }
					</a>
				</td>				
			</tr>
			</table>
		</section>
		<section id="articleContentArea">
			${qna.qna_content }
		</section>
	</section>
			</div>
	<section id="commandList"><c:if test="${not empty sessionScope.sId and sessionScope.sId eq 'admin'}">
		<input type="button" value="답변" onclick="location.href='QnaReplyForm.bo?qna_code=${param.qna_code}&pageNum=${param.pageNum }'"></c:if>
		<c:if test="${sessionScope.sId eq 'admin' || sessionScope.sId == qna.member_id}"> <input type="button" value="수정" onclick="location.href='QnaModifyForm.bo?qna_code=${param.qna_code}&pageNum=${param.pageNum }'">
		<input type="button" value="삭제" onclick="location.href='QnaDeletePro.bo?qna_code=${param.qna_code}&pageNum=${param.pageNum }'"></c:if>
		<input type="button" value="목록" onclick="location.href='QnaList.bo?pageNum=${param.pageNum}'">
	</section>
    <!-- Footer-->
   <footer class="py-5 bg-dark">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>