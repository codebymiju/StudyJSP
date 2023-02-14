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
	  
	#reviewForm {
		width: 500px;
		height: 450px;
		border: 1px solid black;
		margin: auto;
	}	
	h2 {
	    font-family:"GmarketSansMedium" ;
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
	}
	
	.td_left {
		width: 150px;
		background: #D3D3D3;
		text-align: center;
	}
	
	.td_right {
		width: 300px;
	}
	
	#commandCell {
		text-align: center;
		margin-bottom: 30px;
	}
	
	input[type=radio]{
		top: -0.7em;
	}
	
	input, textarea{
	    font-family:"GmarketSansMedium" ;
	    border-radius: 0px;
	}

	textarea {
		resize: none;
	}
		
	input{
	    font-family:"GmarketSansMedium" ;
	    border-radius: 0px;
	}
</style>
</head>
<body>
	
		<jsp:include page="/inc/top.jsp"></jsp:include>
	
	<!-- q&a 등록 -->
	<section id="writeForm">
		<h2>Q & A Write</h2>
		<form action="QnaWritePro.bo" name="qnaForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="qna_code" value="${param.qna_code }" >
		<input type="hidden" name="qna_pass" value="${param.qna_pass }" >
		<input type="hidden" name="member_id" value="${qna.member_id }" >
		<input type="hidden" name="pageNum" value="${param.pageNum }" >
			<table class="table container">
				<tr>
					<td class="td_left"><label for="member_id">비밀글</label></td>
					<td class="td_right">
    				<input type="radio" name="qna_secret" id="qna_secret" value="Y" class="radio" />&nbsp;&nbsp;&nbsp;&nbsp;<span>공개</span>&nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="radio" name="qna_secret" id="qna_secret" value="N" class="radio" />&nbsp;&nbsp;&nbsp;&nbsp;<span>비공개</span>&nbsp;
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td class="td_left"><label for="member_id">작성자</label></td> -->
<!-- 					<td class="td_right"><input type="text" name="member_id" required="required" /></td> -->
<!-- 				</tr> -->
				<tr>
					<td class="td_left"><label for="qna_subject">제목</label></td>
					<td class="td_right"><input type="text" name="qna_subject" required="required" /></td>
					
				</tr>
				<tr>
					<td class="td_left"><label for="qna_content">내용</label></td>
					<td class="td_right">
						<textarea cols="50" rows="15" required="required" name="qna_content" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="qna_file">파일 첨부</label></td>
					<td class="td_right">
						<input type="file" name="qna_file" />
						<br>(기존 파일 : ${qna.qna_file })
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<c:if test="${not empty sessionScope.sId}"><input type="submit" value="등록">&nbsp;&nbsp;</c:if>
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
	  <!-- Footer-->
   <footer class="py-5 bg-dark">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>