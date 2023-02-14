<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="en">
    <head>
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
	
	#modifyForm {
		width: 500px;
		height: 500px;
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
/* 		width: 300px; */
		background: white;
	}
	
	#commandCell {
		text-align: center;
		margin-bottom: 30px;
	}
	
	input[type=button], input[type=submit], input[type=reset], input[type=text] {
	    font-family:"GmarketSansMedium" ;
	}
	
	table > tbody {
		border-style: none;
	}
	
</style>
</head>
<body>
	
		<jsp:include page="/inc/top.jsp"></jsp:include>
	
	<!-- 게시판 글 수정 -->
<!-- 	<section id="modifyForm"> -->
		<h2>Q & A Modify</h2>
		<form action="QnaModifyPro.bo" name="qnaForm" method="post" enctype="multipart/form-data">
			<!-- 입력받지 않은 글번호, 페이지번호 hidden 속성으로 전달 -->
			<input type="hidden" name="qna_code" value="${param.qna_code }" >
			<input type="hidden" name="qna_pass" value="${param.qna_pass }" >
			<input type="hidden" name="pageNum" value="${param.pageNum }" >
			<!-- 파일 수정 시 기존 파일 삭제를 위해 실제 파일명도 파라미터로 전달 필요 -->
			<input type="hidden" name="qna_real_file" value="${qna.qna_real_file }" >
			<table class="table container">
				<tr>
					<td class="td_left"><label for="member_id">작성자</label></td>
					<td class="td_right"><input type="text" name="member_id" value="${qna.member_id }" readonly="readonly"></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td class="td_left"><label for="qna_pass">비밀번호</label></td> -->
<!-- 					<td class="td_right"><input type="password" name="qna_pass" required="required"></td> -->
<!-- 				</tr> -->
				<tr>
					<td class="td_left"><label for="qna_subject">제목</label></td>
					<td class="td_right"><input type="text" name="qna_subject" value="${qna.qna_subject }" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="qna_content">내용</label></td>
					<td class="td_right">
						<textarea id="qna_content" name="qna_content" cols="40" rows="15" required="required">${qna.qna_content }</textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="qna_file">파일</label></td>
					<!-- 파일 수정할 경우에만 선택하도록 required 속성 제거 -->
					<td class="td_right">
						<input type="file" name="qna_file">
						<br>(기존 파일 : ${qna.qna_file })
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<c:if test="${not empty sessionScope.sId}"><input type="submit" value="수정"></c:if>&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>	
    <!-- Footer-->
   <footer class="py-5 bg-dark">
      <div class="container">
         <p class="m-0 text-center text-white">Copyright &copy; DangDangEat 2023</p>
      </div>
   </footer>