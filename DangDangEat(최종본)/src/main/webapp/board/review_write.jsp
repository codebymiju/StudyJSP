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
		width: 1224px;
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
		
	input, textarea {
	    font-family:"GmarketSansMedium" ;
	    border-radius: 0px;
	}
	
	textarea {
		resize: none;
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
	
	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h2>Review Write</h2>
		<!-- 파일 업로드 기능 사용 위해 enctype 속성 설정 => cos.jar 라이브러리 필요 -->
		<form action="ReviewWritePro.bo" name="reviewForm" method="post" enctype="multipart/form-data">
			<table class="table container">
<!-- 				<tr> -->
<!-- 					<td class="td_left"><label for="member_id">작성자</label></td> -->
<!-- 					<td class="td_right"><input type="text" name="member_id" required="required" /></td> -->
<!-- 				</tr> -->
				
<!--  				<tr>  -->
<!-- 					<td class="td_left"><label for="review_pass">비밀번호</label></td> -->
<!-- 					<td class="td_right"> -->
<!-- 						<input type="password" name="review_pass" required="required" /> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td class="td_left"><label for="review_subject">제목</label></td>
					<td class="td_right"><input type="text" name="review_subject" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="review_content">내용</label></td>
					<td class="td_right">
						<textarea cols="50" rows="15" name="review_content" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="review_file">파일 첨부</label></td>
					<td class="td_right">
						<input type="file" name="review_file" />
						<br>(기존 파일 : ${review.review_file })
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp;
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